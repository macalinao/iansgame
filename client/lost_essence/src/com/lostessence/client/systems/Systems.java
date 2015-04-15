/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems;

import com.lostessence.client.MainClient;


/**
 * Abstract class for container classes.
 * 
 * @author simplyianm
 */
public abstract class Systems {
    public MainClient client;
    
    public Systems(MainClient client) {
        this.client = client;
    }
    
    /**
     * Initializes all systems and adds them to
     * the world.
     */
    public abstract void initialize();
    
    /**
     * Processes all systems that the
     * Systems object has defined.
     */
    public abstract void process();
}
