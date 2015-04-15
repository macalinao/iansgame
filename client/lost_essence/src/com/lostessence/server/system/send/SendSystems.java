/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.system.send;

import com.lostessence.server.system.prepare.PrepareMovementSystem;
import com.lostessence.server.system.prepare.PreparePuppetSystem;
import com.lostessence.server.system.prepare.PrepareCameraSystem;
import com.artemis.SystemManager;
import com.lostessence.common.message.MessageDigest;
import com.lostessence.server.MainServer;
import com.lostessence.server.component.SPlayerComponent;
import com.lostessence.server.system.Systems;
import java.util.HashMap;

/**
 * Container object for classes relating to the network and
 * sending data over the internet in general.
 * 
 * @author simplyianm
 */
public class SendSystems extends Systems {
    private SendDigestSystem sendDigestSystem;
    
    public SendSystems(MainServer server) {
        super(server);
    }
    
    @Override
    public void initialize() {
        sendDigestSystem = new SendDigestSystem(server);
        
        SystemManager s = server.getWorld().getSystemManager();
        s.setSystem(sendDigestSystem);
    }
    
    @Override
    public void process() {
        sendDigestSystem.process();
    }
}