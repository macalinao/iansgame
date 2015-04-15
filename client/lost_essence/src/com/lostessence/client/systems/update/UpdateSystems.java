/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.update;

import com.artemis.SystemManager;
import com.lostessence.client.MainClient;
import com.lostessence.client.systems.Systems;

/**
 *
 * @author simplyianm
 */
public class UpdateSystems extends Systems {
    private UpdateCameraSystem updateCameraSystem;
    private UpdateMovementSystem updateMovementSystem;
    private UpdateRenderPositionSystem updateRenderPositionSystem;
    private UpdatePuppetSystem updatePuppetSystem;
    
    public UpdateSystems(MainClient client) {
        super(client);
    }
    
    @Override
    public void initialize() {
        updateCameraSystem = new UpdateCameraSystem(client);
        updateMovementSystem = new UpdateMovementSystem(client);
        updateRenderPositionSystem = new UpdateRenderPositionSystem(client);
        updatePuppetSystem = new UpdatePuppetSystem(client);
        
        SystemManager systemManager = client.getWorld().getSystemManager();
        systemManager.setSystem(updateCameraSystem);
        systemManager.setSystem(updateMovementSystem);
        systemManager.setSystem(updateRenderPositionSystem);
        systemManager.setSystem(updatePuppetSystem);
    }

    @Override
    public void process() {
        updateCameraSystem.process();
        updateMovementSystem.process();
        updateRenderPositionSystem.process();
        updatePuppetSystem.process();
    }
    
}
