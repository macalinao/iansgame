/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lostessence.server;

import com.lostessence.server.network.MessageListener;
import com.lostessence.server.network.KryoServer;
import com.artemis.World;
import com.lostessence.server.network.DigestManager;
import com.lostessence.server.network.MessageQueue;
import com.lostessence.server.network.SortedMessages;
import com.lostessence.server.system.prepare.PrepareSystems;
import com.lostessence.server.system.receive.ReceiveSystems;
import com.lostessence.server.system.send.SendSystems;
import com.lostessence.server.system.update.UpdateSystems;

/**
 * The main server class. There should only
 * be one instance of this class.
 * 
 * @author simplyianm
 */
public class MainServer {
    //Server stuff
    private World world;
    private Ticker ticker;
    
    //Networking stuff
    private KryoServer kryoServer;
    private MessageQueue messageQueue;
    private SortedMessages sortedMessages;
    private DigestManager digestManager;

    //Systems
    private ReceiveSystems receiveSystems;
    private UpdateSystems updateSystems;
    private PrepareSystems prepareSystems;
    private SendSystems sendSystems;
    
    public MainServer() {
        
    }
    
    /**
     * Initializes the server. There isn't a
     * good reason why it isn't within the constructor.
     */
    public void initialize() {
        world = new World();
        
        //Starts the server thread
        kryoServer = new KryoServer(32441);
        kryoServer.addListener(new MessageListener(this));
        kryoServer.start();
        
        //Loads up our message queue and message handling system.
        messageQueue = new MessageQueue(this);
        sortedMessages = new SortedMessages(this);
        digestManager = new DigestManager(this);
        
        //Initialize systems
        receiveSystems = new ReceiveSystems(this);
        updateSystems = new UpdateSystems(this);
        prepareSystems = new PrepareSystems(this);
        sendSystems = new SendSystems(this);
        
        receiveSystems.initialize();
        updateSystems.initialize();
        prepareSystems.initialize();
        sendSystems.initialize();
        
        //Initialize all systems
        world.getSystemManager().initializeAll();
        
        //Set up ticker which basically executes the game loop.
        ticker = new Ticker(this);
        (new Thread(ticker)).start();
    }
    
    /**
     * The main update loop.
     * 
     * <p>The order of the loop, when all done, will go like this:
     * <ol>
     *  <li><strong>Entity Movement</strong> - Process all systems related to the altering of entities based on stuff like velocity.</li>
     *  <li><strong>SendSystems</strong> - Process all systems related to the sending of data.</li>
     * </ol>
     * 
     * @param delta The amount of time since the last loop in milliseconds
     */
    public void update(int delta) {
        //Sort messages
        messageQueue.sort();
        
        //Process Systems
        receiveSystems.process();
        updateSystems.process();
        prepareSystems.process();
        sendSystems.process();
        
        //Reset our queues and stuff
        sortedMessages.clearMessages();
        digestManager.reset();
    }
    
    public KryoServer getKryoServer() {
        return this.kryoServer;
    }

    /**
     * @return the serverWorld
     */
    public World getWorld() {
        return world;
    }

    /**
     * @return the messageQueue
     */
    public MessageQueue getMessageQueue() {
        return messageQueue;
    }

    /**
     * @return the sortedMessages
     */
    public SortedMessages getSortedMessages() {
        return sortedMessages;
    }

    /**
     * 
     * @return The server {@link DigestManager}
     */
    public DigestManager getDigestManager() {
        return digestManager;
    }
}
