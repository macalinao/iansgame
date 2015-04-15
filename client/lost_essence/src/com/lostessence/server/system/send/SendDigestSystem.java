/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.send;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.common.message.MessageDigest;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SPlayerComponent;

/**
 * Sends all of the digests to their respective players.
 * 
 * @author simplyianm
 */
public class SendDigestSystem extends EntityProcessingSystem {
    private ComponentMapper<SPlayerComponent> playerMapper;
    
    private MainServer server;
    
    public SendDigestSystem(MainServer server) {
        super(SPlayerComponent.class);
        this.server = server;
    }
    
    @Override
    protected void initialize() {
        playerMapper = new ComponentMapper(SPlayerComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        SPlayerComponent player = playerMapper.get(entity);
        MessageDigest digest = server.getDigestManager().get(player);
        player.getConnection().sendTCP(digest);
    }
    
}
