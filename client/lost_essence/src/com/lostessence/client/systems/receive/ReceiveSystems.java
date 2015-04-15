/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.receive;

import com.artemis.SystemManager;
import com.lostessence.client.MainClient;
import com.lostessence.client.systems.Systems;

/**
 *
 * @author simplyianm
 */
public class ReceiveSystems extends Systems {
    private ReceiveCameraSystem receiveCameraSystem;
    private ReceivePuppetSystem receiveAppearanceSystem;
    private ReceiveMovementSystem receiveMovementSystem;
    
    public ReceiveSystems(MainClient client) {
        super(client);
    }
    
    @Override
    public void initialize() {
        //Create system objects
        receiveCameraSystem = new ReceiveCameraSystem(client);
        receiveAppearanceSystem = new ReceivePuppetSystem(client);
        receiveMovementSystem = new ReceiveMovementSystem(client);
        
        //Add systems to the world
        SystemManager s = client.getWorld().getSystemManager();
        s.setSystem(receiveCameraSystem);
        s.setSystem(receiveAppearanceSystem);
        s.setSystem(receiveMovementSystem);
    }

    @Override
    public void process() {
        receiveCameraSystem.process();
        receiveAppearanceSystem.process();
        receiveMovementSystem.process();
    }
    
}
