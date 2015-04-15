/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server;

import com.artemis.Entity;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.lostessence.common.message.Message;
import com.lostessence.server.component.ConnectionComponent;
import com.lostessence.server.component.Position;
import com.lostessence.server.component.Rotation;
import com.lostessence.server.component.Velocity;

/**
 *
 * @author simplyianm
 */
public class MessageListener extends Listener {
    private MainServer server;
    
    public MessageListener(MainServer server) {
        this.server = server;
    }
    
    @Override
    public void connected(Connection connection) {
    }
    
    @Override
    public void disconnected(Connection connection) {
        
    }
    
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof Message) {
            Message message = (Message) object;
            switch (message.getType()) {
                case PLAYER_JOIN:
                    System.out.println("A player has joined!");
                    Entity newPlayer = server.getServerWorld().createEntity();
                    newPlayer.addComponent(new ConnectionComponent(connection));
                    newPlayer.addComponent(new Position(30, 30));
                    newPlayer.addComponent(new Rotation(2));
                    newPlayer.addComponent(new Velocity(0f, 0f));
                    newPlayer.refresh();
                    break;
            }
        }
    }
}