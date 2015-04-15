/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.common.message.server.EntityMovementUpdateMessage;
import com.lostessence.server.KryoServer;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.Position;
import com.lostessence.server.component.Rotation;
import com.lostessence.server.component.Velocity;

/**
 *
 * @author simplyianm
 */
public class ServerSendMovementSystem extends EntityProcessingSystem {
    private ComponentMapper<Position> positionMapper;
    private ComponentMapper<Velocity> velocityMapper;
    private ComponentMapper<Rotation> rotationMapper;
    private MainServer server;
    
    public ServerSendMovementSystem(MainServer server) {
        super(Position.class, Velocity.class, Rotation.class);
        this.server = server;
    }
    
    @Override
    public void initialize() {
        positionMapper = new ComponentMapper(Position.class, world);
        velocityMapper = new ComponentMapper(Velocity.class, world);
        rotationMapper = new ComponentMapper(Rotation.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        Position position = positionMapper.get(entity);
        Velocity velocity = velocityMapper.get(entity);
        Rotation rotation = rotationMapper.get(entity);
        
        EntityMovementUpdateMessage message = new EntityMovementUpdateMessage();
        message.setMessage(position.getX(), position.getY(), velocity.getX(), velocity.getY(), rotation.getRotation(), entity.getUniqueId());
        server.getKryoServer().sendToAllTCP(message);
    }
    
}