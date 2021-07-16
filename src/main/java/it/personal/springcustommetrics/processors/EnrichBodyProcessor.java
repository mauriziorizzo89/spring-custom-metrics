package it.personal.springcustommetrics.processors;

import it.personal.springcustommetrics.util.ProcessorConstant;
import it.personal.springcustommetrics.util.Router;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class EnrichBodyProcessor implements Processor {

    public static final int MAX = 100;
    public static final int MIN = 1;
    public static final int THRESHOLD = 50;

    @Override
    public void process(Exchange exchange) throws Exception {
        Random r = new Random();
        int i = r.nextInt((MAX - MIN) + 1) + 1;
        if(i < THRESHOLD) {
            exchange.getIn().setHeader(ProcessorConstant.ROUTER, Router.ONE);
            exchange.getIn().setBody(Router.ONE);
        } else {
            exchange.getIn().setHeader(ProcessorConstant.ROUTER, Router.TWO);
            exchange.getIn().setBody(Router.TWO);
        }
    }

}
