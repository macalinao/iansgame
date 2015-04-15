/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.render;

import com.artemis.SystemManager;
import com.lostessence.client.MainClient;
import com.lostessence.client.systems.Systems;

/**
 *
 * @author simplyianm
 */
public class RenderSystems extends Systems {
    private RenderMapBGMaskSystem renderMapBGMaskSystem;
    private RenderPuppetSystem renderPuppetSystem;
    private RenderMapFringeSystem renderMapFringeSystem;
    private RenderMapCoverSystem renderMapCoverSystem;
    
    public RenderSystems(MainClient client) {
        super(client);
    }
    
    @Override
    public void initialize() {
        renderMapBGMaskSystem = new RenderMapBGMaskSystem(client);
        renderPuppetSystem = new RenderPuppetSystem(client);
        renderMapFringeSystem = new RenderMapFringeSystem(client);
        renderMapCoverSystem = new RenderMapCoverSystem(client);
        
        SystemManager systemManager = client.getWorld().getSystemManager();
        systemManager.setSystem(renderMapBGMaskSystem);
        systemManager.setSystem(renderPuppetSystem);
        systemManager.setSystem(renderMapFringeSystem);
        systemManager.setSystem(renderMapCoverSystem);
    }

    @Override
    public void process() {
        renderMapBGMaskSystem.process();
        renderPuppetSystem.process();
        renderMapFringeSystem.process();
        renderMapCoverSystem.process();
    }
}
