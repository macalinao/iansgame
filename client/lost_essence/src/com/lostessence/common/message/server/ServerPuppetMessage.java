/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.server;

import com.lostessence.common.message.Message;

/**
 * Contains the data for the appearance of an entity.
 * 
 * @author simplyianm
 */
public class ServerPuppetMessage extends ServerMessage {
    private long entityId;

    /**
     * Constructor
     */
    public ServerPuppetMessage() {
        super(Message.Type.SERVER_PUPPET);
    }
    
    /**
     * Sets the data for this message.
     * 
     * @param entityId 
     */
    public void setMessage(long entityId) {
        this.entityId = entityId;
    }

    /**
     * @return the entityId
     */
    public long getEntityId() {
        return entityId;
    }
}