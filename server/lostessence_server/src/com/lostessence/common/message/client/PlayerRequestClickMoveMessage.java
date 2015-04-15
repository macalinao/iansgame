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
public class PlayerRequestClickMoveMessage extends PlayerRequestMoveMessage {
    private double worldX;
    private double worldY;
    
    public void setMessage(double worldX, double worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    @Override
    public MessageType getType() {
        return MessageType.PLAYER_REQUEST_CLICK_MOVE;
    }

    /**
     * @return the worldX
     */
    public double getWorldX() {
        return worldX;
    }

    /**
     * @param worldX the worldX to set
     */
    public void setWorldX(double worldX) {
        this.worldX = worldX;
    }

    /**
     * @return the worldY
     */
    public double getWorldY() {
        return worldY;
    }

    /**
     * @param worldY the worldY to set
     */
    public void setWorldY(double worldY) {
        this.worldY = worldY;
    }
}