/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.component;

import com.artemis.Component;

/**
 * Amount of rotation in radians. Range is -Pi to Pi.
 * 
 * @author simplyianm
 */
public class Rotation extends Component {
    private double rotation;

    public Rotation(double rotation) {
        this.rotation = rotation;
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