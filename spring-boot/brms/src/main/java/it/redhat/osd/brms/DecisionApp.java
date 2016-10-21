package it.redhat.osd.brms;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DecisionApp {

    private static final Logger logger = LoggerFactory.getLogger(DecisionApp.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DecisionApp.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        StringBuilder sb = new StringBuilder("===> Application beans:\n");
        for (String beanName : beanNames) {
            sb.append(beanName + "\n");
        }
        logger.info(sb.toString());
    }

    @Bean
    public KieContainer kieContainer() {
        logger.info("===> Initialising KIE Container.");
        return KieServices.Factory.get().getKieClasspathContainer();
    }

}
