/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.component;

import com.artemis.Component;

/**
 *
 * @author simplyianm
 */
public class CServerIdComponent extends Component {
    private long id;
    
    public CServerIdComponent(long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
