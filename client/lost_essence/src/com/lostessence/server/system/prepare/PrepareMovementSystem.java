/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.prepare;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.common.message.server.ServerMovementMessage;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SPositionComponent;
import com.lostessence.server.component.SRotationComponent;
import com.lostessence.server.component.SVelocityComponent;

/**
 *
 * @author simplyianm
 */
public class PrepareMovementSystem extends EntityProcessingSystem {
    private ComponentMapper<SPositionComponent> positionMapper;
    private ComponentMapper<SVelocityComponent> velocityMapper;
    private ComponentMapper<SRotationComponent> rotationMapper;
    
    private MainServer server;
    
    public PrepareMovementSystem(MainServer server) {
        super(SPositionComponent.class, SVelocityComponent.class, SRotationComponent.class);
        this.server = server;
    }
    
    @Override
    public void initialize() {
        positionMapper = new ComponentMapper(SPositionComponent.class, world);
        velocityMapper = new ComponentMapper(SVelocityComponent.class, world);
        rotationMapper = new ComponentMapper(SRotationComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        SPositionComponent position = positionMapper.get(entity);
        SVelocityComponent velocity = velocityMapper.get(entity);
        SRotationComponent rotation = rotationMapper.get(entity);
        
        ServerMovementMessage message = new ServerMovementMessage();
        message.setMessage(position.getX(), position.getY(), velocity.getX(), velocity.getY(), rotation.getRotation(), entity.getUniqueId());
        server.getDigestManager().addToAll(message);
    }
    
}