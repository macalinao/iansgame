/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.engine.factory;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author simplyianm
 */
public class AnimationFactory {
    public static Animation makeAnimation(SpriteSheet sheet, boolean loop, int row, int duration, int... frames) {
        Animation anim = new Animation(false);
        anim.setLooping(loop);
        for (int i = 0; i < frames.length; i++) {
            anim.addFrame(sheet.getSprite(frames[i], row), duration);
        }
        return anim;
    }
}
