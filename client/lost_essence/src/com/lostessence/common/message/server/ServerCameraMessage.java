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
public class ServerCameraMessage extends ServerMessage {
    private double x;
    private double y;
    private double velX;
    private double velY;
    
    /**
     * Constructor
     */
    public ServerCameraMessage() {
        super(Message.Type.SERVER_CAMERA);
    }

    /**
     * Sets the data that this message contains.
     * 
     * @param x
     * @param y
     * @param velX
     * @param velY 
     */
    public void setMessage(double x, double y, double velX, double velY) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
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
}