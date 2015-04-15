/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.client;

import com.lostessence.common.message.Message;

/**
 * Contains the direction the player is facing.
 * 
 * @author simplyianm
 */
public class ClientPlayerRotateMessage extends ClientMessage {
    private double rotation;

    /**
     * Constructor
     */
    public ClientPlayerRotateMessage() {
        super(Message.Type.CLIENT_PLAYER_ROTATE);
    }
    
    /**
     * Sets the data for this message.
     * 
     * @param rotation 
     */
    public void setMessage(double rotation) {
        this.rotation = rotation;
    }

    /**
     * Gets the amount of rotation in radians the direction the player wishes to move.
     * 
     * @return the rotation
     */
    public double getRotation() {
        return rotation;
    }
    
}
