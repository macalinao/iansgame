/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.update;

import com.artemis.SystemManager;
import com.lostessence.server.MainServer;
import com.lostessence.server.system.Systems;

/**
 *
 * @author simplyianm
 */
public class UpdateSystems extends Systems {
    private UpdateMovementSystem updateMovementSystem;
    
    public UpdateSystems(MainServer server) {
        super(server);
    }
    
    @Override
    public void initialize() {
        updateMovementSystem = new UpdateMovementSystem(server);
        
        SystemManager systemManager = server.getWorld().getSystemManager();
        systemManager.setSystem(updateMovementSystem);
    }

    @Override
    public void process() {
        updateMovementSystem.process();
    }
    
}
