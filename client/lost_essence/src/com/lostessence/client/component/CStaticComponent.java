/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.component;

import com.artemis.Component;
import org.newdawn.slick.Image;

/**
 *
 * @author simplyianm
 */
public class CStaticComponent extends Component {
    private Image image;

    public CStaticComponent(Image image) {
        this.image = image;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
