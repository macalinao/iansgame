/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.event;

/**
 * Represents a listener registered for an event.
 */
public class RegisteredListener {
    private final Listener listener;
    private final Event.Priority priority;

    /**
     * 
     * @param pluginListener
     * @param eventPriority
     */
    public RegisteredListener(final Listener pluginListener, final Event.Priority eventPriority) {
        listener = pluginListener;
        priority = eventPriority;
    }
    
    /**
     * 
     * @return
     */
    public Listener getListener() {
        return listener;
    }
    
    /**
     * 
     * @return
     */
    public Event.Priority getPriority() {
        return priority;
    }
    
    /**
     * 
     * @param event
     */
    public void callEvent(Event event) {
        this.getListener().callEvent(event);
    }
}