/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package websocketbot;

import websocketbot.encoders.UsersMessageEncoder;
import websocketbot.encoders.ChatMessageEncoder;
import websocketbot.encoders.InfoMessageEncoder;
import websocketbot.encoders.JoinMessageEncoder;
import websocketbot.messages.ChatMessage;
import websocketbot.messages.UsersMessage;
import websocketbot.messages.JoinMessage;
import websocketbot.messages.InfoMessage;
import websocketbot.decoders.MessageDecoder;
import websocketbot.messages.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.annotation.Resource;
//import javax.enterprise.concurrent.ManagedExecutorService;
//import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/* Websocket endpoint */
@ServerEndpoint(
        value = "/websocketbot",
        decoders = { MessageDecoder.class }, 
        encoders = { JoinMessageEncoder.class, ChatMessageEncoder.class, 
                     InfoMessageEncoder.class, UsersMessageEncoder.class }
        )
/* There is a BotEndpoint instance per connetion */
public class BotEndpoint {
    private static final Logger logger = Logger.getLogger("BotEndpoint");
    private static final List<Session> setsession=new ArrayList<Session>();
    /* Bot functionality bean */
//    //@Inject
//    private BotStockBean botstockbean = new BotStockBean();
//    /* Executor service for asynchronous processing */
//    @Resource(name="tomcatThreadPool")
//    private ManagedExecutorService mes;
    
    @OnOpen
    public void openConnection(Session session) {
        logger.log(Level.INFO, "Connection opened.");
        setsession.add(session);
    }
    
    @OnMessage
    public void message(final Session session, Message msg) {
        logger.log(Level.INFO, "Received: {0}", msg.toString());
        if (msg instanceof JoinMessage) {
            /* Add the new user and notify everybody */
            JoinMessage jmsg = (JoinMessage) msg;
            session.getUserProperties().put("name", jmsg.getName());
            session.getUserProperties().put("active", true);
            logger.log(Level.INFO, "Received: {0}", jmsg.toString());
            sendAll(new InfoMessage(jmsg.getName() + " has joined the chat"));
            sendAll(new ChatMessage("Duke", jmsg.getName(), "Hi there!!"));
            sendAll(new UsersMessage(this.getUserList()));
            
        } else if (msg instanceof ChatMessage) {
            /* Forward the message to everybody */
            final ChatMessage cmsg = (ChatMessage) msg;
            logger.log(Level.INFO, "Received: {0}", cmsg.toString());
            sendAll(cmsg);
//            if (cmsg.getTarget().compareTo("Duke") == 0) {
//                /* The bot replies to the message */
//            	System.out.println("this is Duke'msg");
//                mes.submit(new Runnable() {
//                    public void run() {
//                        String resp = "Duke";//botstockbean.respond(cmsg.getMessage());
//                        sendAll(session, new ChatMessage("Duke", cmsg.getName(), resp));
//                    }
//                });
//            }
        }
    }
    
    @OnClose
    public void closedConnection(Session session) {
        /* Notify everybody */
        for(int i=0;i<setsession.size();++i){
            if(setsession.get(i).getId().equals(session.getId())){
                setsession.get(i).getUserProperties().put("active",false);
            }
        }
        //session.getUserProperties().put("active", false);
        if (session.getUserProperties().containsKey("name")) {
            String name = session.getUserProperties().get("name").toString();
            sendAll(new InfoMessage(name + " has left the chat"));
            sendAll(new UsersMessage(this.getUserList()));
        }
        logger.log(Level.INFO, "Connection closed.");
    }
    
    @OnError
    public void error(Session session, Throwable t) {
        logger.log(Level.INFO, "Connection error ({0})", t.toString());
    }
    
    /* Forward a message to all connected clients
     * The endpoint figures what encoder to use based on the message type */
    public synchronized void sendAll( Object msg) {
        for(int i=0;i<setsession.size();++i) {
            try {
                for (Session s : setsession.get(i).getOpenSessions()) {
                    if (s.isOpen()) {
                        s.getBasicRemote().sendObject(msg);
                        logger.log(Level.INFO, "Sent: {0}", msg.toString());
                    }
                }
            } catch (IOException e) {
                logger.log(Level.INFO, e.toString());
            } catch (EncodeException e) {
                logger.log(Level.INFO, e.toString());
            }
        }
    }
    
    /* Returns the list of users from the properties of all open sessions */
    public List<String> getUserList() {
        List<String> users = new ArrayList<String>();
        //users.add("Duke");
        for(int i=0;i<setsession.size();++i) {
            for (Session s : setsession.get(i).getOpenSessions()) {
                //todo
                java.lang.Object object = s.getUserProperties().get("active");
                if (s.isOpen())
                    users.add(s.getUserProperties().get("name").toString());
            }
        }
        return users;
    }
}
