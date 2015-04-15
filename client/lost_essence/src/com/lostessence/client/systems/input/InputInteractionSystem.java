/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.input;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CCameraPositionComponent;
import com.lostessence.common.message.client.ClientPlayerInteractMessage;
import org.newdawn.slick.GameContainer;

/**
 *
 * @author simplyianm
 */
public class InputInteractionSystem extends EntitySystem {
    private ComponentMapper<CCameraPositionComponent> cameraPositionMapper;
    
    private final MainClient client;
    
    public InputInteractionSystem(MainClient client) {
        super(CCameraPositionComponent.class);
        this.client = client;
    }
    
    @Override
    protected void initialize() {
        cameraPositionMapper = new ComponentMapper(CCameraPositionComponent.class, world);
    }
    
    @Override
    protected void processEntities(ImmutableBag<Entity> ib) {
        if (ib.isEmpty()) return;
        if (!client.getContainer().getInput().isMousePressed(1)) return;
        
        GameContainer container = client.getContainer();
        
        //Get the camera entity
        Entity cameraEntity = ib.get(0);
        CCameraPositionComponent camPos = cameraPositionMapper.get(cameraEntity);
        
        double worldX = (double) container.getInput().getMouseX() / 32.0 - container.getWidth() / 64.0 + camPos.getX();
        double worldY = (double) container.getInput().getMouseY() / 32.0 - container.getHeight() / 64.0 + camPos.getY();
        
        ClientPlayerInteractMessage message = new ClientPlayerInteractMessage();
        message.setMessage(false, worldX, worldY);
        client.getKryoClient().sendMessage(message);
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
    
}
