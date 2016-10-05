package it.redhat.osd;

import java.util.logging.Logger;

public class EchoService {
	
	private static Logger log = Logger.getLogger(EchoService.class.getName());

    String createEchoMessage(String payload) {
    	log.info("Service called with payload: "+payload);
        return payload ;
    }

}
