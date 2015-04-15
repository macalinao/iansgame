/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.client;

import com.lostessence.common.message.Message;
import com.lostessence.common.message.MessageType;

/**
 *
 * @author simplyianm
 */
public class PlayerJoinMessage implements Message {

    @Override
    public MessageType getType() {
        return MessageType.PLAYER_JOIN;
    }
    
}
