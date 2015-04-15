/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CSpriteAnimationComponent;
import com.lostessence.client.component.CPuppetComponent;
import com.lostessence.client.component.CRenderPositionComponent;
import com.lostessence.client.graphics.SpriteAnimation;
import com.lostessence.util.DoubleComparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import org.newdawn.slick.Animation;

/**
 *
 * @author simplyianm
 */
public class RenderPuppetSystem extends EntityProcessingSystem {
    private ComponentMapper<CSpriteAnimationComponent> animationMapper;
    private ComponentMapper<CRenderPositionComponent> renderPositionMapper;
    
    private HashMap<Entity, Double> entityMap;
    private TreeMap<Entity, Double> sortedEntityMap;
    
    private final MainClient client;
    
    public RenderPuppetSystem(MainClient client) {
        super(CPuppetComponent.class, CSpriteAnimationComponent.class, CRenderPositionComponent.class);
        this.client = client;
    }
    
    @Override
    protected void initialize() {
        animationMapper = new ComponentMapper(CSpriteAnimationComponent.class, world);
        renderPositionMapper = new ComponentMapper(CRenderPositionComponent.class, world);
        
        entityMap = new HashMap<Entity, Double>();
        DoubleComparator dc = new DoubleComparator(entityMap);
        sortedEntityMap = new TreeMap<Entity, Double>(dc);
    }
    
    @Override
    protected void process(Entity entity) {
        CRenderPositionComponent renderPosition = renderPositionMapper.get(entity);
        entityMap.put(entity, Double.valueOf(renderPosition.getY()));
    }
    
    @Override
    protected void end() {
        sortedEntityMap.putAll(entityMap);
        
        Iterator<Entity> it = sortedEntityMap.descendingKeySet().iterator();
        while (it.hasNext()) {
            Entity theEntity = it.next();
            
            CSpriteAnimationComponent spriteAnimation = animationMapper.get(theEntity);
            CRenderPositionComponent renderPosition = renderPositionMapper.get(theEntity);
            SpriteAnimation animation = spriteAnimation.getAnimation();
            Animation current = animation.getCurrent();
            client.getContainer().getGraphics().drawAnimation(current, renderPosition.getX() - (current.getWidth() / 2), renderPosition.getY() - current.getHeight());
        }
        
        entityMap.clear();
        sortedEntityMap.clear();
    }
    
}
