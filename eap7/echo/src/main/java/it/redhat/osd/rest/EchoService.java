package it.redhat.osd.rest;

import java.util.logging.Logger;

public class EchoService {
	
	private static Logger log = Logger.getLogger(EchoService.class.getName());
    
	public EchoResponse createUUIDMessage() {
    	log.info("Service called without payload, returning random UUID... ");
   		return new EchoResponse();
    }
	
	public EchoResponse createEchoMessage(String payload) {
        EchoResponse response = new EchoResponse().setPayload(payload);
    	log.info("Service called with payload: "+payload);
  		return response;
    }

}
