/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.state;

import com.lostessence.client.MainClient;
import de.lessvoid.nifty.slick2d.NiftyGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The main view of the client. Doesn't do
 * much other than call foreign methods.
 * 
 * @author simplyianm
 */
public class InGameState extends NiftyGameState {
    private MainClient client;
    
    public InGameState(MainClient client) {
        super(1);
        this.client = client;
    }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        client.enterGame();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        client.update(delta);
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        client.render();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {}
}