/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CRenderPositionComponent;
import com.lostessence.client.component.CTiledMapComponent;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author simplyianm
 */
public class RenderMapBGMaskSystem extends EntityProcessingSystem {
    private ComponentMapper<CTiledMapComponent> tiledMapComponentMapper;
    private ComponentMapper<CRenderPositionComponent> renderableMapper;
    
    private MainClient client;
    
    public RenderMapBGMaskSystem(MainClient client) {
        super(CTiledMapComponent.class, CRenderPositionComponent.class);
        this.client = client;
    }
    
    @Override
    protected void initialize() {
        tiledMapComponentMapper = new ComponentMapper(CTiledMapComponent.class, world);
        renderableMapper = new ComponentMapper(CRenderPositionComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        CTiledMapComponent mapComp = tiledMapComponentMapper.get(entity);
        CRenderPositionComponent renderPos = renderableMapper.get(entity);
        
        TiledMap map = mapComp.getMap();
        
        //Render positions as integers
        int renderX = (int) renderPos.getX();
        int renderY = (int) renderPos.getY();
        
        //Render offset for map
        int xRenderOffset = renderX % 32;
        int yRenderOffset = renderY % 32;
        
        //Get the first tile to be rendered
        int firstTileX = (int) -renderX / 32;
        int firstTileY = (int) -renderY / 32;
        
        //Render tiles only if their indices are greater than 0
        if (firstTileX < 0) {
            xRenderOffset += Math.abs(firstTileX) * 32;
            firstTileX = 0;
        }
        
        if (firstTileY < 0) {
            yRenderOffset += Math.abs(firstTileY) * 32;
            firstTileY = 0;
        }
        
        //Get the last tile to be rendered
        int lastTileX = firstTileX + (client.getContainer().getWidth() / 32) + 1;
        int lastTileY = firstTileY + (client.getContainer().getHeight() / 32) + 2;
        
        //Verify that the last tile is valid
        if (lastTileX > 127) {
            lastTileX = 127;
        }
        
        if (lastTileY > 127) {
            lastTileY = 127;
        }
        
        //Check if the map will be visible on the screen
        if (lastTileX >= 0 && lastTileY >= 0) {
            map.render(xRenderOffset, yRenderOffset, firstTileX, firstTileY, lastTileX, lastTileY, 0, true);
            map.render(xRenderOffset, yRenderOffset, firstTileX, firstTileY, lastTileX, lastTileY, 1, true);
            map.render(xRenderOffset, yRenderOffset, firstTileX, firstTileY, lastTileX, lastTileY, 2, true);
            map.render(xRenderOffset, yRenderOffset, firstTileX, firstTileY, lastTileX, lastTileY, 3, true);
        }
    }
    
}