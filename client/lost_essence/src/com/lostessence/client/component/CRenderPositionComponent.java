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
public class CRenderPositionComponent extends Component {
    private float x;
    private float y;

    public CRenderPositionComponent(float x, float y) {
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
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }
}
