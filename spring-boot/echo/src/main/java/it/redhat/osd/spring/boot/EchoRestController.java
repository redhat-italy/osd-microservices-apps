package it.redhat.osd.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

@RestController
@RequestMapping("/echo")
public class EchoRestController {
    @RequestMapping(method = RequestMethod.GET, value = "/echo", produces = "text/plain")
    public String echo() throws UnknownHostException {
        return "Ready to go for OSD?";
    }
}