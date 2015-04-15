/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.server;

import com.lostessence.common.message.MessageType;

/**
 *
 * @author simplyianm
 */
public class PersonDataUpdateMessage extends EntityDataUpdateMessage {
    
    @Override
    public MessageType getType() {
        return MessageType.PERSON_DATA_UPDATE;
    }
    
    public void setMessage(long entityId) {
        this.setEntityId(entityId);
    }
}