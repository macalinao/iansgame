/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.lostessence.common.message.client.ClientMessage;
import com.lostessence.common.message.client.ClientPlayerRotateMessage;
import com.lostessence.server.MainServer;

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
        if (object instanceof ClientMessage) {
            ClientMessage message = (ClientMessage) object;
            message.setConnection(connection);
            server.getMessageQueue().add(message);
        }
    }
}