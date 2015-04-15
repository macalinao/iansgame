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
public class ReceiveMovementSystem extends EntityProcessingSystem {
    private ComponentMapper<SPlayerComponent> playerMapper;
    private ComponentMapper<SVelocityComponent> velocityMapper;
    private ComponentMapper<SRotationComponent> rotationMapper;
    
    private HashMap<Connection, ClientPlayerMoveMessage> messages;
    
    private MainServer server;
    
    public ReceiveMovementSystem(MainServer server) {
        super(SPlayerComponent.class, SVelocityComponent.class);
        this.server = server;
    }
    
    @Override
    protected void initialize() {
        messages = new HashMap<Connection, ClientPlayerMoveMessage>();
        
        playerMapper = new ComponentMapper(SPlayerComponent.class, world);
        velocityMapper = new ComponentMapper(SVelocityComponent.class, world);
        rotationMapper = new ComponentMapper(SRotationComponent.class, world);
    }
    
    @Override
    protected void begin() {
        Iterator<ClientPlayerMoveMessage> iterator = server.getSortedMessages().getClientPlayerMoveMessages().iterator();
        while (iterator.hasNext()) {
            ClientPlayerMoveMessage message = iterator.next();
            messages.put(message.getConnection(), message);
        }
    }
    
    @Override
    protected void process(Entity entity) {
        SPlayerComponent player = playerMapper.get(entity);
        
        ClientPlayerMoveMessage message = messages.get(player.getConnection());
        
        if (message != null) {
            SVelocityComponent velocity = velocityMapper.get(entity);
            int direction = message.getDirection();
            
            switch (direction) {
                case 0:
                    velocity.setX(0f);
                    velocity.setY(0f);
                    break;

                case 1:
                    SRotationComponent rotation = rotationMapper.get(entity);
                    velocity.setX(FastMath.cos(rotation.getRotation()) * 5);
                    velocity.setY(FastMath.sin(rotation.getRotation()) * 5);
                    break;

                case 2:
                    velocity.setX(-5);
                    velocity.setY(0);
                    break;

                case 3:
                    velocity.setX(0);
                    velocity.setY(-5);
                    break;

                case 4:
                    velocity.setX(0);
                    velocity.setY(5);
                    break;

                case 5:
                    velocity.setX(5);
                    velocity.setY(0);
                    break;

                case 6:
                    velocity.setX(-5 * 0.70710678118654752440084436210484903928483593768847403658833986899536624);
                    velocity.setY(-5 * 0.70710678118654752440084436210484903928483593768847403658833986899536624);
                    break;

                case 7:
                    velocity.setX(5 * 0.70710678118654752440084436210484903928483593768847403658833986899536624);
                    velocity.setY(-5 * 0.70710678118654752440084436210484903928483593768847403658833986899536624);
                    break;

                case 8:
                    velocity.setX(-5 * 0.70710678118654752440084436210484903928483593768847403658833986899536624);
                    velocity.setY(5 * 0.70710678118654752440084436210484903928483593768847403658833986899536624);
                    break;

                case 9:
                    velocity.setX(5 * 0.70710678118654752440084436210484903928483593768847403658833986899536624);
                    velocity.setY(5 * 0.70710678118654752440084436210484903928483593768847403658833986899536624);
                    break;

                default:
                    velocity.setX(0f);
                    velocity.setY(0f);
                    break;
            }
        }
    }
    
    @Override
    protected void end() {
        messages.clear();
    }
            
}
