/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server;

import com.artemis.EntitySystem;
import com.artemis.SystemManager;
import com.artemis.World;
import com.lostessence.server.system.ServerSendMovementSystem;

/**
 *
 * @author simplyianm
 */
public class MainServer {
    private KryoServer kryoServer;
    private World world;
    private Ticker logicThread;

    private EntitySystem serverSendMovementSystem;
    
    public MainServer() {
        
    }
    
    public void initialize() {
        world = new World();
        
        kryoServer = new KryoServer(32441);
        kryoServer.addListener(new MessageListener(this));
        kryoServer.start();
        
        SystemManager systemManager = world.getSystemManager();
        serverSendMovementSystem = systemManager.setSystem(new ServerSendMovementSystem(this));
        systemManager.initializeAll();
        
        //Set up ticker
        logicThread = new Ticker(this);
        (new Thread(logicThread)).start();
    }
    
    public void update(int delta) {
        serverSendMovementSystem.process();
    }
    
    public KryoServer getKryoServer() {
        return this.kryoServer;
    }

    /**
     * @return the serverWorld
     */
    public World getServerWorld() {
        return world;
    }
}
