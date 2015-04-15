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
    private static final int TICK_LENGTH = 50;
    
    public Ticker(MainServer server) {
        this.server = server;
    }
    
    @Override
    public void run() {
        int delta = 0;
        World serverWorld = server.getWorld();
        long startTime = 0;
        long finishTime = 0;
        boolean running = true;
        while (running) {
            serverWorld.loopStart();
            delta = (int) (finishTime - startTime);
            if (delta <= TICK_LENGTH) {
                try {
                    Thread.sleep(TICK_LENGTH - delta);
                } catch (InterruptedException ex) {}
                delta = TICK_LENGTH;
            }
            serverWorld.setDelta(delta);
            startTime = System.currentTimeMillis();
            server.update(delta);
            finishTime = System.currentTimeMillis();
        }
    }
}
