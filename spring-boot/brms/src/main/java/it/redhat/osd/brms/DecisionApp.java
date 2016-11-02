package it.redhat.osd.brms;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
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
    public final static String GROUPID = "it.redhat.osd.spring-boot";
    public final static String ARTIFACT = "brms";
    public final static String VERSION = "1.0-SNAPSHOT";

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DecisionApp.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        logger.info("===> Starting Decision Server Bean...");
    }

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        ReleaseId id = kieServices.newReleaseId(GROUPID, ARTIFACT, VERSION);
        KieContainer kieContainer = kieServices.newKieContainer(id);
        KieScanner scanner = kieServices.newKieScanner(kieContainer);
        scanner.start(5000);
        return kieContainer;
    }

}
