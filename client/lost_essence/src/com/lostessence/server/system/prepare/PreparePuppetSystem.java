/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.prepare;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.common.message.server.ServerPuppetMessage;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SPuppetComponent;

/**
 *
 * @author simplyianm
 */
public class PreparePuppetSystem extends EntityProcessingSystem {
    private ComponentMapper<SPuppetComponent> puppetMapper;
    private MainServer server;
    
    public PreparePuppetSystem(MainServer server) {
        super(SPuppetComponent.class);
        this.server = server;
    }
    
    @Override
    public void initialize() {
        puppetMapper = new ComponentMapper(SPuppetComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        SPuppetComponent puppet = puppetMapper.get(entity);
        
        ServerPuppetMessage message = new ServerPuppetMessage();
        message.setMessage(entity.getUniqueId());
        server.getDigestManager().addToAll(message);
    }
    
}