/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.event;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author simplyianm
 */
public class SimpleEventManager implements EventManager {
    private Map<String, SortedSet<RegisteredListener>> listeners = new HashMap<String, SortedSet<RegisteredListener>>();
    private final Comparator<RegisteredListener> comparer = new Comparator<RegisteredListener>() {
            public int compare(RegisteredListener i, RegisteredListener j) {
                int result = i.getPriority().compareTo(j.getPriority());
                if ((result == 0) && (i != j)) {
                    result = 1;
                }
                return result;
            }
        };
    
    /**
     * 
     * @param event
     */
    public void callEvent(Event event) {
        SortedSet<RegisteredListener> eventListeners = this.getEventListeners(event.getType());
        if (eventListeners != null) {
            for (RegisteredListener rl : eventListeners) {
                rl.callEvent(event);
            }
        }
    }
    
    /**
     * 
     * @param eventType
     * @param listener
     * @param priority
     */
    public void registerEvent(String eventType, Listener listener, Event.Priority priority) {
        this.getEventListeners(eventType).add(new RegisteredListener(listener, priority));
    }
    
    /**
     * 
     * @param type
     * @return
     */
    public SortedSet<RegisteredListener> getEventListeners(String type) {
        SortedSet<RegisteredListener> eventListeners = this.listeners.get(type);
        if (eventListeners != null) {
            return eventListeners;
        }
        eventListeners = new TreeSet<RegisteredListener>(comparer);
        this.listeners.put(type, eventListeners);
        return eventListeners;
    }
}