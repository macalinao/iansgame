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
public class EntityMovementUpdateMessage extends MovementUpdateMessage {
    private long entityId;
    
    @Override
    public MessageType getType() {
        return MessageType.ENTITY_MOVEMENT_UPDATE;
    }
    
    public void setMessage(double x, double y, double velX, double velY, double rotation, long entityId) {
        super.setMessage(x, y, velX, velY, rotation);
    }
    
    /**
     * @return the entityId
     */
    public long getEntityId() {
        return entityId;
    }

    /**
     * @param entityId the entityId to set
     */
    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}