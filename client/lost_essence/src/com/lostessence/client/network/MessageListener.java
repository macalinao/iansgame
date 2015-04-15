/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.lostessence.client.MainClient;
import com.lostessence.common.message.MessageDigest;

/**
 *
 * @author simplyianm
 */
public class MessageListener extends Listener {
    private MainClient client;
    
    public MessageListener(MainClient client) {
        this.client = client;
    }
    
    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof MessageDigest) {
            MessageDigest digest = (MessageDigest) object;
            client.getMessageQueue().add(digest);
        }
    }
}