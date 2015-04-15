/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.client.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.lostessence.common.message.Message;

/**
 *
 * @author simplyianm
 */
public class KryoClient extends Client {
    public KryoClient() {
        Kryo kryo = this.getKryo();
        
        Message.registerMessages(kryo);
    }
    
    public void sendMessage(Message message) {
        this.sendTCP(message);
    }
}
