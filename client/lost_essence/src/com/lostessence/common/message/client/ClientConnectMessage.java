/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.client;

import com.lostessence.common.message.Message;

/**
 * Sent when a client connects. In the future, it
 * may contain data about logins, hashes, etc. etc.
 * 
 * @author simplyianm
 */
public class ClientConnectMessage extends ClientMessage {
    /**
     * Constructor
     */
    public ClientConnectMessage() {
        super(Message.Type.CLIENT_CONNECT);
    }
}
