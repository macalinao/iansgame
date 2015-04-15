/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.event;

/**
 *
 * @author simplyianm
 */
public class Event {
    private String type;
    
    /**
     * Constructor
     * @param type
     */
    public Event(String type) {
        this.type = type;
    }
    
    /**
     * Determines the order in which the event is executed.
     */
    public enum Priority {
        /**
         * Represents a core event.
         */
        CORE,
        
        /**
         * Represents a single game using the library.
         */
        GAME,
        
        /**
         * Executed first
         */
        HIGHEST,
        
        /**
         * 
         */
        HIGH,
        
        /**
         * 
         */
        NORMAL,
        
        /**
         * 
         */
        LOW,
        
        /**
         * Executed last
         */
        LOWEST;
    }
    
    /**
     * Gets the type of the event.
     * @return
     */
    public String getType() {
        return this.type;
    }
}