/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.network;

import com.lostessence.common.message.Message;
import com.lostessence.common.message.client.ClientConnectMessage;
import com.lostessence.common.message.client.ClientJoinMessage;
import com.lostessence.common.message.client.ClientPlayerInteractMessage;
import com.lostessence.common.message.client.ClientPlayerMoveMessage;
import com.lostessence.common.message.client.ClientPlayerRotateMessage;
import com.lostessence.server.MainServer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author simplyianm
 */
public class MessageQueue {
    private BlockingQueue<Message> queue;
    private MainServer server;
    
    public MessageQueue(MainServer server) {
        this.server = server;
        this.queue = new ArrayBlockingQueue<Message>(2000);
    }
    
    public void sort() {
        SortedMessages sm = server.getSortedMessages();
        List<Message> dump = new ArrayList<Message>();
        queue.drainTo(dump);
        Iterator<Message> iterator = dump.iterator();
        while (iterator.hasNext()) {
            Message m = iterator.next();
            switch (m.getType()) {
                case CLIENT_CONNECT:
                    sm.getClientConnectMessages().add((ClientConnectMessage) m);
                    break;
                    
                case CLIENT_JOIN:
                    sm.getClientJoinMessages().add((ClientJoinMessage) m);
                    break;
                    
                case CLIENT_PLAYER_MOVE:
                    sm.getClientPlayerMoveMessages().add((ClientPlayerMoveMessage) m);
                    break;
                    
                case CLIENT_PLAYER_ROTATE:
                    sm.getClientPlayerRotateMessages().add((ClientPlayerRotateMessage) m);
                    break;
                
                case CLIENT_PLAYER_INTERACT:
                    sm.getClientPlayerInteractMessages().add((ClientPlayerInteractMessage) m);
                    break;
            }
        }
    }
    
    public boolean add(Message message) {
        return queue.add(message);
    }
}
