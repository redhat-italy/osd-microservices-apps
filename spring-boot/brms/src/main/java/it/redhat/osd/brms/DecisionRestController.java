package it.redhat.osd.brms;

import it.redhat.osd.model.Info;
import it.redhat.osd.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DecisionRestController {

    private static final Logger logger = LoggerFactory.getLogger(DecisionRestController.class);
    private final AtomicLong counter = new AtomicLong();
    private final DecisionService decisionService;


    @Autowired
    public DecisionRestController(DecisionService decisionService){
        this.decisionService = decisionService;
    }

    @CrossOrigin
    @RequestMapping(value="/api/brms/ds/info", method = RequestMethod.GET)
    public @ResponseBody
    Info info( ) {
        return new Info().setCounter(counter.incrementAndGet());
    }

    @CrossOrigin
    @RequestMapping(value="/api/brms/ds/order", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Order order(@RequestBody Order order) {
        Order calculatedOrder = decisionService.getResult(order);
        return calculatedOrder;
    }

}
