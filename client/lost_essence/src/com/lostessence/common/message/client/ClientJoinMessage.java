/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.client;

import com.lostessence.common.message.Message;

/**
 *
 * @author simplyianm
 */
public class ClientJoinMessage extends ClientMessage {
    public ClientJoinMessage() {
        super(Message.Type.CLIENT_JOIN);
    }
}
