/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.state;

import com.lostessence.client.MainClient;
import com.lostessence.client.graphics.ResourceManager;
import com.lostessence.common.message.client.ClientConnectMessage;
import de.lessvoid.nifty.slick2d.NiftyGameState;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author simplyianm
 */
public class LoadingState extends NiftyGameState {
    private MainClient client;
    private DeferredResource nextResource;
    private boolean started;
    
    public LoadingState(MainClient client) {
        super(0);
        this.client = client;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) {
        try {
            ResourceManager.loadResources("./res/resources.xml");
        } catch (IOException ex) {
            
        }
        
        try {
            client.getKryoClient().connect(5000, "127.0.0.1", 32441);
            ClientConnectMessage message = new ClientConnectMessage();
            client.getKryoClient().sendTCP(message);
        } catch (IOException ex) {
            Logger.getLogger(LoadingState.class.getName()).log(Level.SEVERE, null, ex);
        }
        game.enterState(1);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        if (nextResource != null) { 
            g.drawString("Loading: " + nextResource.getDescription(), 100, 100); 
        } 
         
        int total = LoadingList.get().getTotalResources(); 
        int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources(); 
         
        float bar = loaded / (float) total; 
        g.fillRect(100,150,loaded * 40,20); 
        g.drawRect(100,150,total * 40,20); 
         
        if (started) {
            g.getFont().drawString(100,500,"LOADING COMPLETE");
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        if (nextResource != null) { 
            try { 
                nextResource.load();
            } catch (IOException e) { 
                try {
                    throw new SlickException("Failed to load a resource " + nextResource.getDescription(), e);
                } catch (SlickException ex) {
                    Logger.getLogger(LoadingState.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            nextResource = null; 
        } 
         
        if (LoadingList.get().getRemainingResources() > 0) { 
            nextResource = LoadingList.get().getNext(); 
        } else { 
            if (!started) { 
                started = true;
            } 
        }
    }
}
