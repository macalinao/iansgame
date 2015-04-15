/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.client;

import com.lostessence.common.message.MessageType;

/**
 *
 * @author simplyianm
 */
public class PlayerRequestDirectionMoveMessage extends PlayerRequestMoveMessage {
    private double rotation;
    
    @Override
    public MessageType getType() {
        return MessageType.PLAYER_REQUEST_DIRECTION_MOVE;
    }
    
    public void setMessage(double rotation) {
        this.setRotation(rotation);
    }

    /**
     * @return the rotation
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * @param rotation the rotation to set
     */
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}