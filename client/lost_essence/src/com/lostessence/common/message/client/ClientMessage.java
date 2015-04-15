/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.client;

import com.esotericsoftware.kryonet.Connection;
import com.lostessence.common.message.Message;

/**
 *
 * @author simplyianm
 */
public abstract class ClientMessage extends Message {
    private Connection connection;
    
    public ClientMessage(Message.Type type) {
        super(type);
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
