/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.server;

import com.lostessence.common.message.Message;

/**
 *
 * @author simplyianm
 */
public abstract class ServerMessage extends Message{
    public ServerMessage(Message.Type type) {
        super(type);
    }
}
