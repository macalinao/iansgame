/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.component;

import com.artemis.Component;

/**
 *
 * @author simplyianm
 */
public class CCameraPositionComponent extends Component {
    private double x;
    private double y;

    public CCameraPositionComponent(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
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
}