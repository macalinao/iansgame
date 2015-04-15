/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import com.lostessence.common.message.client.*;
import com.lostessence.common.message.server.*;

/**
 *
 * @author simplyianm
 */
public class KryoServer extends Server {
    public KryoServer(int tcpPort) {
        Kryo kryo = this.getKryo();
        
        kryo.register(PlayerConnectMessage.class);
        kryo.register(PlayerJoinMessage.class);
        
        kryo.register(CameraMovementUpdateMessage.class);
        kryo.register(EntityMovementUpdateMessage.class);
        kryo.register(MovementUpdateMessage.class);
        
        try {
            this.bind(tcpPort);
        } catch (IOException ex) {
            System.out.print("Could not bind to port " + tcpPort + "; perhaps a server is already running on that port?");
        }
    }
}