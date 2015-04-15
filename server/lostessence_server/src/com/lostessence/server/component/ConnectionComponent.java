/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.component;

import com.artemis.Component;
import com.esotericsoftware.kryonet.Connection;

/**
 *
 * @author simplyianm
 */
public class ConnectionComponent extends Component {
    private Connection connection;

    public ConnectionComponent(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
