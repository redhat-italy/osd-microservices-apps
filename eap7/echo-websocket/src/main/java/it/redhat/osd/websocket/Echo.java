package it.redhat.osd.websocket;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * A simple WebSocket service which is able to echo using EchoService. 
 *
 * @author gbonocor@redhat.com
 *
 */


@ServerEndpoint("/websocket/echo")
public class Echo {
	
	private static Logger log = Logger.getLogger(Echo.class.getName());
	
	
    @Inject
    EchoService echoService;

    @OnMessage
    public String echo(String payload) {
    	log.info("Received message: " + payload );
        return echoService.createEchoMessage(payload);
    }

    @OnOpen
    public void echo(Session session) {
    	log.info("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void helloOnClose(CloseReason reason) {
    	log.info("WebSocket connection closed with CloseCode: " + reason.getCloseCode());
    }

}
