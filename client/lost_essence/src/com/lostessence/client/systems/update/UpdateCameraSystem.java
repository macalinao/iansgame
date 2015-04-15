/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.update;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CCameraPositionComponent;
import com.lostessence.client.component.CVelocityComponent;

/**
 * Updates the camera's movement to keep motion fluid.
 * 
 * @author simplyianm
 */
public class UpdateCameraSystem extends EntityProcessingSystem {
    private ComponentMapper<CCameraPositionComponent> cameraPositionMapper;
    private ComponentMapper<CVelocityComponent> velocityMapper;
    
    private MainClient client;
    
    public UpdateCameraSystem(MainClient client) {
        super(CCameraPositionComponent.class, CVelocityComponent.class);
        this.client = client;
    }
    
    @Override
    public void initialize() {
        cameraPositionMapper = new ComponentMapper(CCameraPositionComponent.class, world);
        velocityMapper = new ComponentMapper(CVelocityComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        CCameraPositionComponent position = cameraPositionMapper.get(entity);
        CVelocityComponent velocity = velocityMapper.get(entity);
        
        double addX = (double) world.getDelta() / 1000.0 * velocity.getX();
        double addY = (double) world.getDelta() / 1000.0 * velocity.getY();
        
        position.setX(position.getX() + addX);
        position.setY(position.getY() + addY);
    }
}