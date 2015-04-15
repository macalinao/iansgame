/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.receive;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.artemis.utils.FastMath;
import com.esotericsoftware.kryonet.Connection;
import com.lostessence.common.message.client.ClientPlayerMoveMessage;
import com.lostessence.common.message.client.ClientPlayerRotateMessage;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SPlayerComponent;
import com.lostessence.server.component.SRotationComponent;
import com.lostessence.server.component.SVelocityComponent;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author simplyianm
 */
public class ReceiveRotationSystem extends EntityProcessingSystem {
    private ComponentMapper<SPlayerComponent> playerMapper;
    private ComponentMapper<SRotationComponent> rotationMapper;
    
    private HashMap<Connection, ClientPlayerRotateMessage> messages;
    
    private MainServer server;
    
    public ReceiveRotationSystem(MainServer server) {
        super(SPlayerComponent.class, SRotationComponent.class);
        this.server = server;
    }
    
    @Override
    protected void initialize() {
        messages = new HashMap<Connection, ClientPlayerRotateMessage>();
        
        playerMapper = new ComponentMapper(SPlayerComponent.class, world);
        rotationMapper = new ComponentMapper(SRotationComponent.class, world);
    }
    
    @Override
    protected void begin() {
        Iterator<ClientPlayerRotateMessage> iterator = server.getSortedMessages().getClientPlayerRotateMessages().iterator();
        while (iterator.hasNext()) {
            ClientPlayerRotateMessage message = iterator.next();
            messages.put(message.getConnection(), message);
        }
    }
    
    @Override
    protected void process(Entity entity) {
        SPlayerComponent player = playerMapper.get(entity);
        
        ClientPlayerRotateMessage message = messages.get(player.getConnection());
        
        if (message != null) {
            SRotationComponent rotation = rotationMapper.get(entity);
            rotation.setRotation(message.getRotation());
        }
    }
    
    @Override
    protected void end() {
        messages.clear();
    }
            
}