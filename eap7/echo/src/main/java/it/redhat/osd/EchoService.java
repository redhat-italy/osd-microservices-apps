package it.redhat.osd;

import java.util.UUID;
import java.util.logging.Logger;

public class EchoService {
	
	private static Logger log = Logger.getLogger(EchoService.class.getName());

    
	String createUUIDMessage() {
    	log.info("Service called without payload, returning random UUID... ");
   		return UUID.randomUUID().toString();
    }
	
	String createEchoMessage(String payload) {
    	log.info("Service called with payload: "+payload);
  		return payload ;
    }
    
    

}
