/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CCameraPositionComponent;
import com.lostessence.client.component.CRenderPositionComponent;
import com.lostessence.client.component.CTiledMapComponent;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author simplyianm
 */
public class RenderMapCoverSystem extends EntityProcessingSystem {
    private ComponentMapper<CTiledMapComponent> tiledMapComponentMapper;
    private ComponentMapper<CRenderPositionComponent> renderableMapper;
    
    private MainClient client;
    
    public RenderMapCoverSystem(MainClient client) {
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
        
        //Get the tile the camera is on
        int xTile = (int) (-(renderX - (client.getContainer().getWidth() / 2)) / 32.0);
        int yTile = (int) (-(renderY - (client.getContainer().getHeight() / 2)) / 32.0);
        
        int cover1CenterId = 0;
        int cover2CenterId = 0;
        
        if (xTile >= 0 && xTile <= 127 && yTile >= 0 && yTile <= 127) {
            cover1CenterId = map.getTileId(xTile, yTile, 6);
            cover2CenterId = map.getTileId(xTile, yTile, 7);
        }
        
        if (cover1CenterId == 0 && cover2CenterId == 0) {
            if (lastTileX >= 0 && lastTileY >= 0) {
                map.render(xRenderOffset, yRenderOffset, firstTileX, firstTileY, lastTileX, lastTileY, 6, true);
                map.render(xRenderOffset, yRenderOffset, firstTileX, firstTileY, lastTileX, lastTileY, 7, true);
            }
        }
    }
    
}