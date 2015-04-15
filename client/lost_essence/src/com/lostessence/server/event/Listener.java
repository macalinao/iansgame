/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.event;

/**
 *
 * @author simplyianm
 */
public abstract class Listener {
    private final String prefix;
    
    /**
     * 
     * @param prefix
     */
    public Listener(String prefix) {
        this.prefix = prefix;
    }
    
    /**
     * 
     * @return
     */
    public String getPrefix() {
        return this.prefix;
    }
    
    /**
     * 
     * @param event
     */
    public abstract void callEvent(Event event);
}