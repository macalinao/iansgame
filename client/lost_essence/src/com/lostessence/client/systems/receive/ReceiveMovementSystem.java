/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.receive;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CPositionComponent;
import com.lostessence.client.component.CRotationComponent;
import com.lostessence.client.component.CServerIdComponent;
import com.lostessence.client.component.CVelocityComponent;
import com.lostessence.common.message.server.ServerMovementMessage;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Processes all received movement.
 * 
 * @author simplyianm
 */
public class ReceiveMovementSystem extends EntityProcessingSystem {
    private MainClient client;
    
    private ComponentMapper<CPositionComponent> positionMapper;
    private ComponentMapper<CVelocityComponent> velocityMapper;
    private ComponentMapper<CRotationComponent> rotationMapper;
    private ComponentMapper<CServerIdComponent> serverIdMapper;
    
    private HashMap<Long, ServerMovementMessage> messages;
    
    public ReceiveMovementSystem(MainClient client) {
        super(CPositionComponent.class, CVelocityComponent.class, CRotationComponent.class, CServerIdComponent.class);
        this.client = client;
    }

    @Override
    protected void initialize() {
        messages = new HashMap<Long, ServerMovementMessage>();
        positionMapper = new ComponentMapper(CPositionComponent.class, world);
        velocityMapper = new ComponentMapper(CVelocityComponent.class, world);
        rotationMapper = new ComponentMapper(CRotationComponent.class, world);
        serverIdMapper = new ComponentMapper(CServerIdComponent.class, world);
    }
    
    @Override
    protected void begin() {
        Iterator<ServerMovementMessage> iterator = client.getSortedMessages().getServerMovementMessages().iterator();
        while (iterator.hasNext()) {
            ServerMovementMessage message = iterator.next();
            messages.put(message.getEntityId(), message);
        }
    }
    
    @Override
    protected void process(Entity entity) {
        CPositionComponent position = positionMapper.get(entity);
        CVelocityComponent velocity = velocityMapper.get(entity);
        CRotationComponent rotation = rotationMapper.get(entity);
        CServerIdComponent serverId = serverIdMapper.get(entity);
        
        ServerMovementMessage message = messages.get(Long.valueOf(serverId.getId()));
        if (message != null) {
            position.setX(message.getX());
            position.setY(message.getY());
            velocity.setX(message.getVelX());
            velocity.setY(message.getVelY());
            rotation.setRotation(message.getRotation());
        }
    }
    
    @Override
    protected void end() {
        messages.clear();
    }
}
