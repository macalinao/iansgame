/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.client;

import com.lostessence.common.message.Message;

/**
 *
 * @author simplyianm
 */
public class ClientPlayerInteractMessage extends ClientMessage {
    private boolean secondary;
    private double worldX;
    private double worldY;
    
    public ClientPlayerInteractMessage() {
        super(Message.Type.CLIENT_PLAYER_INTERACT);
    }

    public void setMessage(boolean secondary, double worldX, double worldY) {
        this.secondary = secondary;
        this.worldX = worldX;
        this.worldY = worldY;
    }
    
    public boolean isSecondary() {
        return secondary;
    }
    
    public double getWorldX() {
        return worldX;
    }

    public double getWorldY() {
        return worldY;
    }

    @Override
    public String toString() {
        return "ClientPlayerInteractMessage{" + "secondary=" + secondary + ", worldX=" + worldX + ", worldY=" + worldY + '}';
    }
    
}
