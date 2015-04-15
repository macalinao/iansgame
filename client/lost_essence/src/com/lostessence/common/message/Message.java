/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message;

import com.esotericsoftware.kryo.Kryo;
import com.lostessence.common.message.client.ClientConnectMessage;
import com.lostessence.common.message.client.ClientJoinMessage;
import com.lostessence.common.message.client.ClientMessage;
import com.lostessence.common.message.client.ClientPlayerInteractMessage;
import com.lostessence.common.message.client.ClientPlayerMoveMessage;
import com.lostessence.common.message.client.ClientPlayerRotateMessage;
import com.lostessence.common.message.server.ServerPuppetMessage;
import com.lostessence.common.message.server.ServerCameraMessage;
import com.lostessence.common.message.server.ServerMessage;
import com.lostessence.common.message.server.ServerMovementMessage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simplyianm
 */
public abstract class Message {
    private Type type;
    
    public Message(Type type) {
        this.type = type;
    }
    
    public Type getType() {
        return this.type;
    }
    
    /**
     * Refers to who sent the message.
     */
    public enum Category {
        /**
         * A message sent by the client. (A request)
         */
        CLIENT,
        
        /**
         * A message sent by the server. (A response)
         */
        SERVER;
    }
    
    public enum Type {
        /*
         * Client requests
         */
        
        /**
         * Sent when a client connects to the server.
         * 
         * @see ClientConnectMessage
         */
        CLIENT_CONNECT (Category.CLIENT),
        
        /**
         * Sent when a client joins the game. (goes inside of the actual world)
         * 
         * @see ClientJoinMessage
         */
        CLIENT_JOIN (Category.CLIENT),
        
        /**
         * Sent every game tick to check where the client is looking.
         * 
         * @see ClientPlayerRotateMessage
         */
        CLIENT_PLAYER_ROTATE (Category.CLIENT),
        
        /**
         * Sent when a client decides to move in the current direction or stop.
         * 
         * @see ClientPlayerMoveMessage
         */
        CLIENT_PLAYER_MOVE (Category.CLIENT),
        
        /**
         * Sent when a player interacts with an object.
         * 
         * @see ClientPlayerInteractMessage
         */
        CLIENT_PLAYER_INTERACT (Category.CLIENT),
        
        /*
         * Server responses 
         */
        
        /**
         * Sent when something moves. (Different from animates)
         * 
         * @see ServerMovementMessage
         */
        SERVER_MOVEMENT (Category.SERVER),
        
        /**
         * Sent when a client's camera is altered.
         * 
         * @see ServerCameraMessage
         */
        SERVER_CAMERA (Category.SERVER),
        
        /**
         * Sent when an entity's appearance changes or is first seen.
         */
        SERVER_PUPPET (Category.SERVER);
        
        private Category category;
        
        private Type(Category category) {
            this.category = category;
        }
        
        public Category getCategory() {
            return this.category;
        }
    }
    
    public static void registerMessages(Kryo kryo) {
        //Register used classes
        kryo.register(List.class);
        kryo.register(ArrayList.class);
        
        //Register basic messages
        kryo.register(Message.class);
        kryo.register(Message.Type.class);
        kryo.register(Message.Category.class);
        kryo.register(MessageDigest.class);
        kryo.register(ClientMessage.class);
        kryo.register(ServerMessage.class);
        
        //Register client messages
        kryo.register(ClientConnectMessage.class);
        kryo.register(ClientJoinMessage.class);
        kryo.register(ClientPlayerRotateMessage.class);
        kryo.register(ClientPlayerMoveMessage.class);
        kryo.register(ClientPlayerInteractMessage.class);
        
        //Register server messages
        kryo.register(ServerPuppetMessage.class);
        kryo.register(ServerCameraMessage.class);
        kryo.register(ServerMovementMessage.class);
    }
}
