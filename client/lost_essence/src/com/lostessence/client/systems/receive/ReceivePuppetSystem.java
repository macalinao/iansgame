/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.receive;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.graphics.ResourceManager;
import com.lostessence.client.component.CSpriteAnimationComponent;
import com.lostessence.client.component.CPositionComponent;
import com.lostessence.client.component.CPuppetComponent;
import com.lostessence.client.component.CRotationComponent;
import com.lostessence.client.component.CServerIdComponent;
import com.lostessence.client.component.CVelocityComponent;
import com.lostessence.client.graphics.SpriteAnimation;
import com.lostessence.common.message.server.ServerPuppetMessage;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Makes new entities if the server tells the client to;
 * otherwise it just updates the appearance of existing entities.
 * 
 * @author simplyianm
 */
public class ReceivePuppetSystem extends EntityProcessingSystem {
    private ComponentMapper<CServerIdComponent> serverIdMapper;
    
    private MainClient client;
    private HashMap<Long, ServerPuppetMessage> messages;
    
    public ReceivePuppetSystem(MainClient client) {
        super(CServerIdComponent.class);
        this.client = client;
    }

    @Override
    protected void initialize() {
        messages = new HashMap<Long, ServerPuppetMessage>();
        
        serverIdMapper = new ComponentMapper(CServerIdComponent.class, world);
    }
    
    @Override
    protected void begin() {
        Iterator<ServerPuppetMessage> iterator = client.getSortedMessages().getServerAppearanceMessages().iterator();
        while (iterator.hasNext()) {
            ServerPuppetMessage message = iterator.next();
            messages.put(message.getEntityId(), message);
        }
    }
    
    @Override
    protected void process(Entity entity) {
        CServerIdComponent serverId = serverIdMapper.get(entity);
        
        ServerPuppetMessage message = messages.get(serverId.getId());
        if (message != null) {
            messages.remove(serverId.getId());
        }
    }
    
    @Override
    protected void end() {
        Collection<ServerPuppetMessage> serverPuppetMessages = messages.values();
        Iterator<ServerPuppetMessage> it = serverPuppetMessages.iterator();
        while (it.hasNext()) {
            ServerPuppetMessage message = it.next();
            
            //Create a new entity if the entity doesn't exist.
            Entity newEntity = world.createEntity();
            newEntity.addComponent(new CServerIdComponent(message.getEntityId()));
            newEntity.addComponent(new CPositionComponent(0, 0));
            newEntity.addComponent(new CVelocityComponent(0, 0));
            newEntity.addComponent(new CRotationComponent(0));
            //the puppet message should somehow include appearance...
            newEntity.addComponent(new CSpriteAnimationComponent(new SpriteAnimation(ResourceManager.getSpriteSheet("rmxp"), 0)));
            newEntity.addComponent(new CPuppetComponent());
            newEntity.refresh();
        }
    }
}
