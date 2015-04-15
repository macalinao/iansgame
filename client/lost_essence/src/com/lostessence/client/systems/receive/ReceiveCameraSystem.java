/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.receive;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CCameraPositionComponent;
import com.lostessence.client.component.CVelocityComponent;
import com.lostessence.common.message.server.ServerCameraMessage;
import java.util.Iterator;

/**
 *
 * @author simplyianm
 */
public class ReceiveCameraSystem extends EntityProcessingSystem {
    private ComponentMapper<CCameraPositionComponent> cameraPositionMapper;
    private ComponentMapper<CVelocityComponent> velocityMapper;
    
    private ServerCameraMessage latestCameraMessage;
    
    private MainClient client;
    
    public ReceiveCameraSystem(MainClient client) {
        super(CCameraPositionComponent.class, CVelocityComponent.class);
        this.client = client;
    }
    
    @Override
    public void initialize() {
        cameraPositionMapper = new ComponentMapper(CCameraPositionComponent.class, world);
        velocityMapper = new ComponentMapper(CVelocityComponent.class, world);
    }
    
    @Override
    protected void begin() {
        Iterator<ServerCameraMessage> iterator = client.getSortedMessages().getServerCameraMessages().iterator();
        while (iterator.hasNext()) {
            latestCameraMessage = iterator.next();
        }
    }
    
    @Override
    protected void process(Entity entity) {
        if (latestCameraMessage != null) {
            CCameraPositionComponent camPosition = cameraPositionMapper.get(entity);
            CVelocityComponent camVelocity = velocityMapper.get(entity);
            camPosition.setX(latestCameraMessage.getX());
            camPosition.setY(latestCameraMessage.getY());
            camVelocity.setX(latestCameraMessage.getVelX());
            camVelocity.setY(latestCameraMessage.getVelY());
            latestCameraMessage = null;
        }
    }
    
}
