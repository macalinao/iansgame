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
public class ServerMovementMessage extends ServerMessage {
    private double x;
    private double y;
    private double velX;
    private double velY;
    private long entityId;
    private double rotation;
    
    /**
     * Constructor
     */
    public ServerMovementMessage() {
        super(Message.Type.SERVER_MOVEMENT);
    }
    
    /**
     * Sets the data that this message contains.
     * 
     * @param x
     * @param y
     * @param velX
     * @param velY
     * @param rotation
     * @param entityId 
     */
    public void setMessage(double x, double y, double velX, double velY, double rotation, long entityId) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.rotation = rotation;
        this.entityId = entityId;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the velX
     */
    public double getVelX() {
        return velX;
    }

    /**
     * @return the velY
     */
    public double getVelY() {
        return velY;
    }

    /**
     * @return the entityId
     */
    public long getEntityId() {
        return entityId;
    }

    /**
     * @return the rotation
     */
    public double getRotation() {
        return rotation;
    }
}
