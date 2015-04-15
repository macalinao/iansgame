/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.client;

import com.lostessence.common.message.Message;

/**
 * Contains the direction the player desires to move in.
 * 
 * @author simplyianm
 */
public class ClientPlayerMoveMessage extends ClientMessage {
    private int direction;
    
    /**
     * Constructor
     */
    public ClientPlayerMoveMessage() {
        super(Message.Type.CLIENT_PLAYER_MOVE);
    }
    
    /**
     * The direction we'll be moving or the command we will be issuing.
     * 
     * Stop = 0
     * Go = 1
     * 
     * A = 2
     * W = 3
     * S = 4
     * D = 5
     * 
     * AW = 6
     * WD = 7
     * AS = 8
     * SD = 9
     * 
     * @param direction 
     */
    public void setMessage(int direction) {
        this.direction = direction;
    }

    /**
     * WASD reasons
     * 
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "ClientPlayerMoveMessage{" + "direction=" + direction + '}';
    }
}