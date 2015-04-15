/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.event;

/**
 *
 * @author simplyianm
 */
public interface EventManager {
    public void callEvent(Event event);
    public void registerEvent(String eventType, Listener listener, Event.Priority priority);
}
