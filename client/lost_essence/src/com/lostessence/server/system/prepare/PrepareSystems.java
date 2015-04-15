/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.prepare;

import com.artemis.SystemManager;
import com.lostessence.server.MainServer;
import com.lostessence.server.system.Systems;
import com.lostessence.server.system.send.PrepareDigestSystem;

/**
 * Systems related to preparing the digest.
 * 
 * @author simplyianm
 */
public class PrepareSystems extends Systems {
    private PrepareDigestSystem prepareDigestSystem;
    private PrepareCameraSystem prepareCameraSystem;
    private PrepareMovementSystem prepareMovementSystem;
    private PreparePuppetSystem prepareAppearanceSystem;
    
    public PrepareSystems(MainServer server) {
        super(server);
    }
    
    @Override
    public void initialize() {
        prepareDigestSystem = new PrepareDigestSystem(server);
        prepareCameraSystem = new PrepareCameraSystem(server);
        prepareMovementSystem = new PrepareMovementSystem(server);
        prepareAppearanceSystem = new PreparePuppetSystem(server);
        
        SystemManager s = server.getWorld().getSystemManager();
        s.setSystem(prepareDigestSystem);
        s.setSystem(prepareCameraSystem);
        s.setSystem(prepareMovementSystem);
        s.setSystem(prepareAppearanceSystem);
    }

    @Override
    public void process() {
        prepareDigestSystem.process();
        prepareCameraSystem.process();
        prepareMovementSystem.process();
        prepareAppearanceSystem.process();
    }
    
}
