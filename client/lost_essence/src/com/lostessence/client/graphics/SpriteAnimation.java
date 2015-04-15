/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author simplyianm
 */
public class SpriteAnimation {
    private Animation upAnimation;
    private Animation downAnimation;
    private Animation leftAnimation;
    private Animation rightAnimation;
    
    private SpriteSheet sheet;
    private int row;
    
    private int lastDirection;
    private int direction;
    private Animation current;
    private boolean moving;
    
    public SpriteAnimation(SpriteSheet sheet, int row) {
        this.sheet = sheet;
        this.row = row;
        this.initialize();
    }
    
    public final void initialize() {
        upAnimation = new Animation(false);
        upAnimation.addFrame(sheet.getSprite(1, row), 200);
        upAnimation.addFrame(sheet.getSprite(0, row), 200);
        upAnimation.addFrame(sheet.getSprite(1, row), 200);
        upAnimation.addFrame(sheet.getSprite(2, row), 200);
        
        downAnimation = new Animation(false);
        downAnimation.addFrame(sheet.getSprite(4, row), 200);
        downAnimation.addFrame(sheet.getSprite(3, row), 200);
        downAnimation.addFrame(sheet.getSprite(4, row), 200);
        downAnimation.addFrame(sheet.getSprite(5, row), 200);
        
        leftAnimation = new Animation(false);
        leftAnimation.addFrame(sheet.getSprite(8, row), 200);
        leftAnimation.addFrame(sheet.getSprite(7, row), 200);
        leftAnimation.addFrame(sheet.getSprite(8, row), 200);
        leftAnimation.addFrame(sheet.getSprite(6, row), 200);
        
        rightAnimation = new Animation(false);
        rightAnimation.addFrame(sheet.getSprite(11, row), 200);
        rightAnimation.addFrame(sheet.getSprite(10, row), 200);
        rightAnimation.addFrame(sheet.getSprite(11, row), 200);
        rightAnimation.addFrame(sheet.getSprite(9, row), 200);
        
        current = downAnimation;
        this.moving = false;
    }
    
    /**
     * Updates the current animation.
     * 
     * @param delta 
     */
    public void update(int delta) {
        //Do we need to switch the animation?
        if (lastDirection != direction) {
            switch(direction) {
                case 0:
                    current = leftAnimation;
                    break;
                case 1:
                    current = upAnimation;
                    break;
                case 2:
                    current = downAnimation;
                    break;
                case 3:
                    current = rightAnimation;
                    break;
            }
        }
        lastDirection = direction;
        
        //Should we make the animation move?
        if (moving) {
            current.update(delta);
        } else if (current.getFrame() != 0) {
            current.setCurrentFrame(0);
        }
        
        //Update the animation
    }

    /**
     * 
     * @return the current animation
     */
    public Animation getCurrent() {
        return current;
    }
    
    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}