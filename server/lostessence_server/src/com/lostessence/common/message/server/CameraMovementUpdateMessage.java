/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.common.message.server;

import com.lostessence.common.message.MessageType;

/**
 *
 * @author simplyianm
 */
public class CameraMovementUpdateMessage extends MovementUpdateMessage {

    @Override
    public MessageType getType() {
        return MessageType.CAMERA_MOVEMENT_UPDATE;
    }

}