/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.component;

import com.artemis.Component;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author simplyianm
 */
public class CTiledMapComponent extends Component {
    private TiledMap map;

    public CTiledMapComponent(TiledMap map) {
        this.map = map;
    }

    /**
     * @return the map
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(TiledMap map) {
        this.map = map;
    }
}
