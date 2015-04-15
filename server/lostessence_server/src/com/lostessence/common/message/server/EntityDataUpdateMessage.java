/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.server;

import com.lostessence.common.message.Message;

/**
 *
 * @author simplyianm
 */
public abstract class EntityDataUpdateMessage implements Message {
    private long entityId;

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
