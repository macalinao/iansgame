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
public abstract class MovementUpdateMessage implements Message {
    private double x;
    private double y;
    private double velX;
    private double velY;
    private double rotation;

    public void setMessage(double x, double y, double velX, double velY, double rotation) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.rotation = rotation;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
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

    /**
     * @return the velX
     */
    public double getVelX() {
        return velX;
    }

    /**
     * @param velX the velX to set
     */
    public void setVelX(double velX) {
        this.velX = velX;
    }

    /**
     * @return the velY
     */
    public double getVelY() {
        return velY;
    }

    /**
     * @param velY the velY to set
     */
    public void setVelY(double velY) {
        this.velY = velY;
    }
}
