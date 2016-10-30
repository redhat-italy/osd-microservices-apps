package it.redhat.osd.services;

import java.util.logging.Logger;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import it.redhat.osd.model.Order;

import java.io.Serializable;


public class OrderService implements Serializable {

	private static Logger log = Logger.getLogger(OrderService.class.getName());
	
	private KieContainer kieContainer;
			
	public OrderService() {
		
		log.info("===> Initialising KIE Container.");
		kieContainer = KieServices.Factory.get().getKieClasspathContainer();
	}

	public Order getResult(Order order){
		log.info("===> working with KIE Session.");
        KieSession kieSession = kieContainer.newKieSession("OSDKSession");
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
}
}
