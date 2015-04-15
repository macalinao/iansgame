/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.lostessence.common.message.Message;
import java.io.IOException;

/**
 *
 * @author simplyianm
 */
public class KryoServer extends Server {
    public KryoServer(int tcpPort) {
        Kryo kryo = this.getKryo();
        
        Message.registerMessages(kryo);
        
        try {
            this.bind(tcpPort);
        } catch (IOException ex) {
            System.out.print("Could not bind to port " + tcpPort + "; perhaps a server is already running on that port?");
        }
    }
}