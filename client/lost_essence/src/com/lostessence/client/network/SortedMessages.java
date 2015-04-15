/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.network;

import com.lostessence.client.MainClient;
import com.lostessence.common.message.server.ServerPuppetMessage;
import com.lostessence.common.message.server.ServerCameraMessage;
import com.lostessence.common.message.server.ServerMovementMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Messages sorted, ready to be parsed.
 * 
 * @author simplyianm
 */
public class SortedMessages {
    private List<ServerCameraMessage> serverCameraMessages = new ArrayList<ServerCameraMessage>();
    private List<ServerMovementMessage> serverMovementMessages = new ArrayList<ServerMovementMessage>();
    private List<ServerPuppetMessage> serverPuppetMessages = new ArrayList<ServerPuppetMessage>();
    private MainClient server;
    
    public SortedMessages(MainClient server) {
        this.server = server;
    }

    /**
     * Clears all message lists.
     */
    public void clearMessages() {
        getServerCameraMessages().clear();
        getServerMovementMessages().clear();
        getServerAppearanceMessages().clear();
    }

    /**
     * @return the serverCameraMessages
     */
    public List<ServerCameraMessage> getServerCameraMessages() {
        return serverCameraMessages;
    }

    /**
     * @return the serverMovementMessages
     */
    public List<ServerMovementMessage> getServerMovementMessages() {
        return serverMovementMessages;
    }

    /**
     * @return the serverAppearanceMessages
     */
    public List<ServerPuppetMessage> getServerAppearanceMessages() {
        return serverPuppetMessages;
    }
}