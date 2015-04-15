/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.component;

import com.artemis.Component;

/**
 *
 * @author simplyianm
 */
public class SPuppetComponent extends Component {
    private String name;

    public SPuppetComponent(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
