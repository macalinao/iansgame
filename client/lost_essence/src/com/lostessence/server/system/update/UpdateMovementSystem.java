/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.update;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SPositionComponent;
import com.lostessence.server.component.SVelocityComponent;

/**
 *
 * @author simplyianm
 */
public class UpdateMovementSystem extends EntityProcessingSystem {
    private ComponentMapper<SPositionComponent> positionMapper;
    private ComponentMapper<SVelocityComponent> velocityMapper;
    
    private final MainServer server;
    
    public UpdateMovementSystem(MainServer server) {
        super(SPositionComponent.class, SVelocityComponent.class);
        this.server = server;
    }
    
    @Override
    protected void initialize() {
        positionMapper = new ComponentMapper(SPositionComponent.class, world);
        velocityMapper = new ComponentMapper(SVelocityComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        SPositionComponent position = positionMapper.get(entity);
        SVelocityComponent velocity = velocityMapper.get(entity);
        
        position.setX(position.getX() + (double) world.getDelta() / 1000.0 * velocity.getX());
        position.setY(position.getY() + (double) world.getDelta() / 1000.0 * velocity.getY());
    }
    
}
