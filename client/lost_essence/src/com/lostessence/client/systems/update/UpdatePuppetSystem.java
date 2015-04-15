/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.systems.update;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.lostessence.client.MainClient;
import com.lostessence.client.component.CSpriteAnimationComponent;
import com.lostessence.client.component.CPuppetComponent;
import com.lostessence.client.component.CRotationComponent;
import com.lostessence.client.component.CVelocityComponent;
import com.lostessence.client.graphics.SpriteAnimation;

/**
 * Makes puppets move.
 * 
 * @author simplyianm
 */
public class UpdatePuppetSystem extends EntityProcessingSystem {
    private ComponentMapper<CRotationComponent> rotationMapper;
    private ComponentMapper<CVelocityComponent> velocityMapper;
    private ComponentMapper<CSpriteAnimationComponent> spriteAnimationMapper;
    
    private final MainClient client;
    
    public UpdatePuppetSystem(MainClient client) {
        super(CPuppetComponent.class, CRotationComponent.class, CVelocityComponent.class, CSpriteAnimationComponent.class);
        this.client = client;
    }
    
    @Override
    protected void initialize() {
        rotationMapper = new ComponentMapper(CRotationComponent.class, world);
        velocityMapper = new ComponentMapper(CVelocityComponent.class, world);
        spriteAnimationMapper = new ComponentMapper(CSpriteAnimationComponent.class, world);
    }
    
    @Override
    protected void process(Entity entity) {
        CRotationComponent rotation = rotationMapper.get(entity);
        CVelocityComponent velocity = velocityMapper.get(entity);
        CSpriteAnimationComponent spriteAnimation = spriteAnimationMapper.get(entity);
        
        double rot = rotation.getRotation();
        SpriteAnimation sprite = spriteAnimation.getAnimation();
        
        int direction = 3; //right
        if (Math.abs(rot) > Math.PI * 0.75) { //left
            direction = 0;
        } else if (rot < -Math.PI / 4) { //up
            direction = 1;
        } else if (rot > Math.PI / 4) { //down
            direction = 2;
        }
        
        if (sprite.getDirection() != direction) {
            sprite.setDirection(direction);
        }
        
        if (velocity.getX() != 0f || velocity.getY() != 0f) {
            if (!sprite.isMoving()) {
                sprite.setMoving(true);
            }
        } else if (sprite.isMoving()) {
            sprite.setMoving(false);
        }
        
        sprite.update(world.getDelta());
    }
}
