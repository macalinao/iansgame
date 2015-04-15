/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.update;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CPositionComponent;
import com.lostessence.client.component.CRenderPositionComponent;

/**
 *
 * @author simplyianm
 */
public class UpdateRenderPositionSystem extends EntityProcessingSystem {
    private MainClient client;
    private ComponentMapper<CPositionComponent> positionMapper;
    private ComponentMapper<CRenderPositionComponent> renderPositionMapper;
    
    public UpdateRenderPositionSystem(MainClient client) {
        super(CPositionComponent.class);
        this.client = client;
    }

    @Override
    public void initialize() {
        positionMapper = new ComponentMapper(CPositionComponent.class, world);
        renderPositionMapper = new ComponentMapper(CRenderPositionComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        CPositionComponent position = positionMapper.get(entity);
        CRenderPositionComponent renderPosition = renderPositionMapper.get(entity);
        if (renderPosition == null) {
            renderPosition = new CRenderPositionComponent(0f, 0f);
            entity.addComponent(renderPosition);
            entity.refresh();
        }
        
        renderPosition.setX((float) ((position.getX() - client.getCameraPosition().getX())) * 32 + client.getContainer().getWidth() / 2);
        renderPosition.setY((float) ((position.getY() - client.getCameraPosition().getY())) * 32 + client.getContainer().getHeight() / 2);
    }
}
