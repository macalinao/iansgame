/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server;

import com.artemis.World;

/**
 *
 * @author simplyianm
 */
public class Ticker implements Runnable {
    private MainServer server;
    
    public Ticker(MainServer server) {
        this.server = server;
    }
    
    @Override
    public void run() {
        int delta = 0;
        World serverWorld = server.getServerWorld();
        long startTime = 0;
        long finishTime = 0;
        boolean running = true;
        while (running) {
            serverWorld.loopStart();
            delta = (int) (finishTime - startTime);
            if (delta <= 50) {
                try {
                    Thread.sleep(50 - delta);
                } catch (InterruptedException ex) {}
                delta = 50;
            }
            serverWorld.setDelta(delta);
            startTime = System.currentTimeMillis();
            server.update(delta);
            finishTime = System.currentTimeMillis();
        }
    }
}
