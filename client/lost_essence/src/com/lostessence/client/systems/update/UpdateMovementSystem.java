/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.update;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CPositionComponent;
import com.lostessence.client.component.CVelocityComponent;

/**
 *
 * @author simplyianm
 */
public class UpdateMovementSystem extends EntityProcessingSystem {
    private ComponentMapper<CPositionComponent> positionMapper;
    private ComponentMapper<CVelocityComponent> velocityMapper;
    
    private final MainClient client;
    
    public UpdateMovementSystem(MainClient client) {
        super(CPositionComponent.class, CVelocityComponent.class);
        this.client = client;
    }

    @Override
    public void initialize() {
        positionMapper = new ComponentMapper(CPositionComponent.class, world);
        velocityMapper = new ComponentMapper(CVelocityComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        CPositionComponent position = positionMapper.get(entity);
        CVelocityComponent velocity = velocityMapper.get(entity);
        
        position.setX(position.getX() + (double) world.getDelta() / 1000.0 * velocity.getX());
        position.setY(position.getY() + (double) world.getDelta() / 1000.0 * velocity.getY());
    }
}
