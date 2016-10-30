package it.redhat.osd.brms;

import it.redhat.osd.model.Order;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecisionService {

    private static final Logger logger = LoggerFactory.getLogger(DecisionService.class);
    private final KieContainer kieContainer;

    @Autowired
    public DecisionService(KieContainer kieContainer){
        logger.info("===> Initialising KIE Session.");
        this.kieContainer = kieContainer;
    }

    public Order applyRules(Order order){
        KieSession kieSession = kieContainer.newKieSession("OSDKSession");
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }
}
