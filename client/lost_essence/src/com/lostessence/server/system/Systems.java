/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system;

import com.lostessence.server.MainServer;

/**
 * Abstract class for container classes.
 * 
 * @author simplyianm
 */
public abstract class Systems {
    public MainServer server;
    
    public Systems(MainServer server) {
        this.server = server;
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
