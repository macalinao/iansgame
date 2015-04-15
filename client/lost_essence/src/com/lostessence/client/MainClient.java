/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client;

import com.lostessence.client.state.MainGame;
import com.artemis.Entity;
import com.artemis.World;
import com.lostessence.client.component.CCameraPositionComponent;
import com.lostessence.client.component.CPositionComponent;
import com.lostessence.client.component.CTiledMapComponent;
import com.lostessence.client.component.CVelocityComponent;
import com.lostessence.client.network.KryoClient;
import com.lostessence.client.network.MessageListener;
import com.lostessence.client.network.MessageQueue;
import com.lostessence.client.network.SortedMessages;
import com.lostessence.client.systems.input.InputSystems;
import com.lostessence.client.systems.receive.ReceiveSystems;
import com.lostessence.client.systems.render.RenderSystems;
import com.lostessence.client.systems.update.UpdateSystems;
import com.lostessence.common.message.client.ClientPlayerMoveMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * The main client class.
 * 
 * @author simplyianm
 */
public class MainClient {
    private AppGameContainer container;
    
    private MainGame game;
    private KryoClient kryoClient;
    private World world;
    private CCameraPositionComponent cameraPosition;
    
    private SortedMessages sortedMessages;
    private MessageQueue messageQueue;
    
    private ReceiveSystems receiveSystems;
    private UpdateSystems updateSystems;
    private InputSystems inputSystems;
    private RenderSystems renderSystems;
    
    public MainClient() {
        
    }
    
    /**
     * Initializes the client and makes the game
     * ready to play.
     */
    public void initialize() {
        //Start up networking
        kryoClient = new KryoClient();
        kryoClient.addListener(new MessageListener(this));
        kryoClient.start();
        
        //Initialize the message queue
        messageQueue = new MessageQueue(this);
        sortedMessages = new SortedMessages(this);
        
        //Create game stuff
        world = new World();
        game = new MainGame(this);
        
        //Initialize systems
        receiveSystems = new ReceiveSystems(this);
        updateSystems = new UpdateSystems(this);
        inputSystems = new InputSystems(this);
        renderSystems = new RenderSystems(this);
        
        receiveSystems.initialize();
        updateSystems.initialize();
        inputSystems.initialize();
        renderSystems.initialize();
        
        //Initialize all systems
        world.getSystemManager().initializeAll();
        
        //Create the camera
        Entity cameraEntity = world.createEntity();
        cameraPosition = new CCameraPositionComponent(0, 0);
        CVelocityComponent cameraVelocity = new CVelocityComponent(0f, 0f);
        cameraEntity.addComponent(cameraPosition);
        cameraEntity.addComponent(cameraVelocity);
        cameraEntity.refresh();
        
        
        
        //Start up the game
        try {
            container = new AppGameContainer(game);
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException ex) {
            System.out.print("Could not create game container.");
        }
    }

    /**
     * Called when the main part of the game is entered.
     */
    public void enterGame() {
        //TODO: Debug code
        Entity tiledMap = world.createEntity();
        try {
            tiledMap.addComponent(new CTiledMapComponent(new TiledMap("/maps/overworld/map_0_0.tmx")));
        } catch (SlickException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        tiledMap.addComponent(new CPositionComponent(0.0, 0.0));
        tiledMap.refresh();
    }
    
    /**
     * The game update loop
     * 
     * @param delta The elapsed time in milliseconds since the last update
     */
    public void update(int delta) {
        //Start the loop
        world.loopStart();
        world.setDelta(delta);
        
        //Sort the message queue
        messageQueue.sort();

        //Process anything incoming
        receiveSystems.process();
        inputSystems.process();
        updateSystems.process();
        
        //Clear all stuff
        sortedMessages.clearMessages();
    }

    /**
     * The render loop
     */
    public void render() {
        renderSystems.process();
    }
    
    /**
     * @return the container
     */
    public AppGameContainer getContainer() {
        return container;
    }

    /**
     * @return the kryoClient
     */
    public KryoClient getKryoClient() {
        return kryoClient;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets the current CameraPosition object in use.
     * 
     * @return the cameraPosition
     */
    public CCameraPositionComponent getCameraPosition() {
        return cameraPosition;
    }

    /**
     * @return the sortedMessages
     */
    public SortedMessages getSortedMessages() {
        return sortedMessages;
    }

    /**
     * @return the messageQueue
     */
    public MessageQueue getMessageQueue() {
        return messageQueue;
    }
}