/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.network;

import com.lostessence.common.message.Message;
import com.lostessence.common.message.MessageDigest;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SPlayerComponent;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author simplyianm
 */
public class DigestManager {
    private HashMap<SPlayerComponent, MessageDigest> digestMap;
    
    private final MainServer server;
    
    public DigestManager(MainServer server) {
        digestMap = new HashMap<SPlayerComponent, MessageDigest>();
        this.server = server;
    }
    
    /**
     * Creates a digest for a player.
     * 
     * @param player 
     */
    public void create(SPlayerComponent player) {
        digestMap.put(player, new MessageDigest());
    }
    
    /**
     * Adds a message to a certain player.
     * 
     * @param player
     * @param message 
     */
    public void add(SPlayerComponent player, Message message) {
        digestMap.get(player).add(message);
    }
    
    /**
     * Adds a message to all messages.
     * 
     * @param message 
     */
    public void addToAll(Message message) {
        Iterator<MessageDigest> iterator = digestMap.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().add(message);
        }
    }
    
    /**
     * Gets a MessageDigest of a player.
     * 
     * @param player
     * @return 
     */
    public MessageDigest get(SPlayerComponent player) {
        return digestMap.get(player);
    }
    
    /**
     * Resets all digests.
     */
    public void reset() {
        digestMap.clear();
    }
}
