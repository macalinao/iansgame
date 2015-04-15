/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.state;

import com.lostessence.client.MainClient;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author simplyianm
 */
public class MainGame extends StateBasedGame {
    private MainClient client;
    
    public MainGame(MainClient client) {
        super("Lost Essence v0.0.0.1");
        this.client = client;
    }

    @Override
    public void initStatesList(GameContainer container) {
        this.addState(new LoadingState(client));
        InGameState gameView = new InGameState(client);
        this.addState(gameView);
        this.enterState(0);
    }
}
