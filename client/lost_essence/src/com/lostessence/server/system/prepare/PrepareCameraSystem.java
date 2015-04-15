/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.prepare;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.common.message.server.ServerCameraMessage;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SCameraComponent;
import com.lostessence.server.component.SPlayerComponent;
import com.lostessence.server.component.SPositionComponent;
import com.lostessence.server.component.SVelocityComponent;

/**
 *
 * @author simplyianm
 */
public class PrepareCameraSystem extends EntityProcessingSystem {
    private MainServer server;
    
    private ComponentMapper<SPositionComponent> positionMapper;
    private ComponentMapper<SVelocityComponent> velocityMapper;
    private ComponentMapper<SPlayerComponent> playerMapper;
    
    
    public PrepareCameraSystem(MainServer server) {
        super(SCameraComponent.class, SPositionComponent.class, SVelocityComponent.class, SPlayerComponent.class);
        this.server = server;
    }
    
    @Override
    public void initialize() {
        positionMapper = new ComponentMapper(SPositionComponent.class, world);
        velocityMapper = new ComponentMapper(SVelocityComponent.class, world);
        playerMapper = new ComponentMapper(SPlayerComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        SPositionComponent position = positionMapper.get(entity);
        SVelocityComponent velocity = velocityMapper.get(entity);
        SPlayerComponent player = playerMapper.get(entity);
        ServerCameraMessage message = new ServerCameraMessage();
        message.setMessage(position.getX(), position.getY(), velocity.getX(), velocity.getY());
        
        server.getDigestManager().add(player, message);
    }
    
}
