/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.component;

import com.artemis.Component;
import com.lostessence.client.graphics.SpriteAnimation;

/**
 *
 * @author simplyianm
 */
public class CSpriteAnimationComponent extends Component {
    private SpriteAnimation animation;

    public CSpriteAnimationComponent(SpriteAnimation animation) {
        this.animation = animation;
    }

    /**
     * @return the animation
     */
    public SpriteAnimation getAnimation() {
        return animation;
    }

    /**
     * @param animation the animation to set
     */
    public void setAnimation(SpriteAnimation animation) {
        this.animation = animation;
    }
}
