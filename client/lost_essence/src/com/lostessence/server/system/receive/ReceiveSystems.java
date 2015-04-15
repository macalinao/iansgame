/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.receive;

import com.artemis.Entity;
import com.artemis.SystemManager;
import com.lostessence.common.message.client.ClientConnectMessage;
import com.lostessence.common.message.client.ClientPlayerInteractMessage;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SCameraComponent;
import com.lostessence.server.component.SPlayerComponent;
import com.lostessence.server.component.SPositionComponent;
import com.lostessence.server.component.SPuppetComponent;
import com.lostessence.server.component.SRotationComponent;
import com.lostessence.server.component.SVelocityComponent;
import com.lostessence.server.system.Systems;
import java.util.Iterator;
import java.util.List;

/**
 * Systems related to the processing of received data.
 * 
 * @author simplyianm
 */
public class ReceiveSystems extends Systems {
    private ReceiveRotationSystem receiveRotationSystem;
    private ReceiveMovementSystem receiveMovementSystem;
    
    public ReceiveSystems(MainServer server) {
        super(server);
    }
    
    @Override
    public void initialize() {
        receiveRotationSystem = new ReceiveRotationSystem(server);
        receiveMovementSystem = new ReceiveMovementSystem(server);
        
        SystemManager systemManager = server.getWorld().getSystemManager();
        systemManager.setSystem(receiveRotationSystem);
        systemManager.setSystem(receiveMovementSystem);
    }

    @Override
    public void process() {
        this.connectPlayers();
        this.checkInteracts();
        receiveRotationSystem.process();
        receiveMovementSystem.process();
    }
    
    public void connectPlayers() {
        List<ClientConnectMessage> clientConnectMessages = server.getSortedMessages().getClientConnectMessages();
        Iterator<ClientConnectMessage> iterator = clientConnectMessages.iterator();
        while (iterator.hasNext()) {
            ClientConnectMessage message = iterator.next();
            System.out.println("A player has connected!");
            SPlayerComponent con = new SPlayerComponent(message.getConnection());

            Entity newCamera = server.getWorld().createEntity();
            newCamera.addComponent(con);
            newCamera.addComponent(new SCameraComponent());
            newCamera.addComponent(new SPositionComponent(40, 40));
            newCamera.addComponent(new SRotationComponent(2));
            newCamera.addComponent(new SVelocityComponent(0f, 0f));
            newCamera.refresh();

            Entity newPlayer = server.getWorld().createEntity();
            newPlayer.addComponent(con);
            newPlayer.addComponent(new SPuppetComponent("Bobbert"));
            newPlayer.addComponent(new SPositionComponent(40, 40));
            newPlayer.addComponent(new SRotationComponent(2));
            newPlayer.addComponent(new SVelocityComponent(0f, 0f));
            newPlayer.refresh();
        }
    }
    
    public void checkInteracts() {
        List<ClientPlayerInteractMessage> clientPlayerInteractMessages = server.getSortedMessages().getClientPlayerInteractMessages();
        Iterator<ClientPlayerInteractMessage> iterator = clientPlayerInteractMessages.iterator();
        while (iterator.hasNext()) {
            ClientPlayerInteractMessage message = iterator.next();
            System.out.println(message);
        }
    }
}