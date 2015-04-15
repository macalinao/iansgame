/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.input;

import com.artemis.SystemManager;
import com.lostessence.client.MainClient;
import com.lostessence.client.systems.Systems;
import com.lostessence.common.message.client.ClientPlayerMoveMessage;
import com.lostessence.common.message.client.ClientPlayerRotateMessage;
import org.newdawn.slick.Input;

/**
 * Contains systems relating to the processing of user input.
 * 
 * @author simplyianm
 */
public class InputSystems extends Systems {
    private InputInteractionSystem inputInteractionSystem;
    
    private int curDir;
    
    private double xWhenRequested;
    private double yWhenRequested;
    private double distanceGoalSquared;
    
    public InputSystems(MainClient client) {
        super(client);
    }
    
    @Override
    public void initialize() {
        inputInteractionSystem = new InputInteractionSystem(client);
        
        SystemManager systemManager = client.getWorld().getSystemManager();
        systemManager.setSystem(inputInteractionSystem);
        
        curDir = 0;
    }

    @Override
    public void process() {
        checkRotation();
        checkMovement();
        inputInteractionSystem.process();
    }
    
    /**
     * Checks for if the mouse has moved; if so then send a new rotation.
     */
    public void checkRotation() {
        //if (!client.getContainer().getInput().) //TODO: check if the mouse has moved
        
        //Get the amount of rotation from the camera
        double rot = Math.atan2(
                client.getContainer().getInput().getMouseY() - client.getContainer().getHeight() / 2,
                client.getContainer().getInput().getMouseX() - client.getContainer().getWidth() / 2);
        
        ClientPlayerRotateMessage message = new ClientPlayerRotateMessage();
        message.setMessage(rot);
        client.getKryoClient().sendMessage(message);
    }
    
    /**
     * Checks for movement requests and executed them.
     * 
     * TODO: Make this not work when clicking on windows, etc.
     */
    public void checkMovement() {
        //Get the distance differences
        double diffX = Math.abs(client.getCameraPosition().getX() - xWhenRequested);
        double diffY = Math.abs(client.getCameraPosition().getY() - yWhenRequested);
        
        //Stop if the requested distance is reached
        if (distanceGoalSquared != 0 && diffX * diffX + diffY * diffY > distanceGoalSquared) {
            distanceGoalSquared = 0;
            xWhenRequested = 0;
            yWhenRequested = 0;
            
            ClientPlayerMoveMessage message = new ClientPlayerMoveMessage();
            message.setMessage(0);
            client.getKryoClient().sendMessage(message);
        }
        
        //Get input
        Input input = client.getContainer().getInput();
        
        //Get keys
        boolean wKey = input.isKeyDown(Input.KEY_W);
        boolean aKey = input.isKeyDown(Input.KEY_A);
        boolean sKey = input.isKeyDown(Input.KEY_S);
        boolean dKey = input.isKeyDown(Input.KEY_D);
        
        //First check if the player wants to do WASD
        if (wKey || aKey || sKey || dKey) {
            distanceGoalSquared = 0;
            int newDir = 1;
            if (wKey) {
                if (aKey) {
                    newDir = 6;
                } else if (dKey) {
                    newDir = 7;
                } else {
                    newDir = 3;
                }
            } else if (aKey) {
                if (sKey) {
                    newDir = 8;
                } else {
                    newDir = 2;
                }
            } else if (sKey) {
                if (dKey) {
                    newDir = 9;
                } else {
                    newDir = 4;
                }
            } else {
                newDir = 5;
            }
            
            //Send a message only if the direction has changed.
            if (newDir != curDir && newDir != 0) {
                distanceGoalSquared = 0;
                ClientPlayerMoveMessage message = new ClientPlayerMoveMessage();
                message.setMessage(newDir);
                client.getKryoClient().sendMessage(message);
                curDir = newDir;
            }
        } else if (curDir != 0) {
            //Send stop message if not pressing WASD any more.
            ClientPlayerMoveMessage message = new ClientPlayerMoveMessage();
            message.setMessage(0);
            client.getKryoClient().sendMessage(message);
            curDir = 0;
        } else if (client.getContainer().getInput().isMousePressed(0)) {
            //Set the click distance
            double distX = ((client.getContainer().getWidth() / 2) - input.getMouseX()) / 32.0;
            double distY = ((client.getContainer().getHeight() / 2) - input.getMouseY()) / 32.0;
            distanceGoalSquared = distX * distX + distY * distY;
            
            xWhenRequested = client.getCameraPosition().getX();
            yWhenRequested = client.getCameraPosition().getY();
            
            //Create and send a movement message
            ClientPlayerMoveMessage message = new ClientPlayerMoveMessage();
            message.setMessage(1);
            client.getKryoClient().sendMessage(message);
        }
    }
}
