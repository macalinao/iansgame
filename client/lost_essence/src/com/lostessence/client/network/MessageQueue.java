/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.network;

import com.lostessence.client.MainClient;
import com.lostessence.common.message.Message;
import com.lostessence.common.message.MessageDigest;
import com.lostessence.common.message.server.ServerPuppetMessage;
import com.lostessence.common.message.server.ServerCameraMessage;
import com.lostessence.common.message.server.ServerMovementMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Container object for all unsorted messages.
 * 
 * @author simplyianm
 */
public class MessageQueue {
    private BlockingQueue<MessageDigest> queue;
    private MainClient client;
    
    public MessageQueue(MainClient client) {
        this.client = client;
        this.queue = new ArrayBlockingQueue<MessageDigest>(1000);
    }
    
    /**
     * Sorts the messages.
     */
    public void sort() {
        SortedMessages sm = client.getSortedMessages();
        List<MessageDigest> dump = new ArrayList<MessageDigest>();
        queue.drainTo(dump);
        Iterator<MessageDigest> iterator = dump.iterator();
        while (iterator.hasNext()) {
            Iterator<Message> digestIterator = iterator.next().getDigestContent().iterator();
            while (digestIterator.hasNext()) {
                Message m = digestIterator.next();
                switch (m.getType()) {
                    case SERVER_CAMERA:
                        sm.getServerCameraMessages().add((ServerCameraMessage) m);
                        break;

                    case SERVER_MOVEMENT:
                        sm.getServerMovementMessages().add((ServerMovementMessage) m);
                        break;

                    case SERVER_PUPPET:
                        sm.getServerAppearanceMessages().add((ServerPuppetMessage) m);
                        break;
                }
            }
        }
    }
    
    /**
     * Adds another digest to the queue. This
     * should only be called once per game loop.
     * 
     * @param message
     * @return 
     */
    public boolean add(MessageDigest message) {
        return queue.add(message);
    }
}
