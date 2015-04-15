/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.network;

import com.lostessence.common.message.client.ClientConnectMessage;
import com.lostessence.common.message.client.ClientJoinMessage;
import com.lostessence.common.message.client.ClientPlayerInteractMessage;
import com.lostessence.common.message.client.ClientPlayerMoveMessage;
import com.lostessence.common.message.client.ClientPlayerRotateMessage;
import com.lostessence.server.MainServer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simplyianm
 */
public class SortedMessages {
    private List<ClientConnectMessage> clientConnectMessages = new ArrayList<ClientConnectMessage>();
    private List<ClientJoinMessage> clientJoinMessages = new ArrayList<ClientJoinMessage>();
    private List<ClientPlayerMoveMessage> clientPlayerMoveMessages = new ArrayList<ClientPlayerMoveMessage>();
    private List<ClientPlayerRotateMessage> clientPlayerRotateMessages = new ArrayList<ClientPlayerRotateMessage>();
    private List<ClientPlayerInteractMessage> clientPlayerInteractMessages = new ArrayList<ClientPlayerInteractMessage>();
    
    private MainServer server;
    
    public SortedMessages(MainServer server) {
        this.server = server;
    }

    public void clearMessages() {
        clientConnectMessages.clear();
        clientJoinMessages.clear();
        clientPlayerMoveMessages.clear();
        clientPlayerRotateMessages.clear();
        clientPlayerInteractMessages.clear();
    }
    
    /**
     * @return the clientConnectMessages
     */
    public List<ClientConnectMessage> getClientConnectMessages() {
        return clientConnectMessages;
    }

    /**
     * @return the clientJoinMessages
     */
    public List<ClientJoinMessage> getClientJoinMessages() {
        return clientJoinMessages;
    }

    /**
     * @return the clientPlayerMoveMessages
     */
    public List<ClientPlayerMoveMessage> getClientPlayerMoveMessages() {
        return clientPlayerMoveMessages;
    }

    /**
     * @return the clientPlayerRotateMessages
     */
    public List<ClientPlayerRotateMessage> getClientPlayerRotateMessages() {
        return clientPlayerRotateMessages;
    }

    public List<ClientPlayerInteractMessage> getClientPlayerInteractMessages() {
        return clientPlayerInteractMessages;
    }
}