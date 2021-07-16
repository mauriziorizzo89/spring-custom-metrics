package it.personal.springcustommetrics.processors;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import it.personal.springcustommetrics.component.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public abstract class AbstractMetricProcessor implements Processor {

    private final Counter counter;

    public AbstractMetricProcessor(MeterRegistry meterRegistry) {
        counter = meterRegistry.counter(getCustomCounter());
        log.info("START - Counter: [{}] - initial value: [{}]", getCustomCounter(), counter);
    }

    protected abstract String getCustomCounter();

    @Override
    public void process(Exchange exchange) throws Exception {
        counter.increment();
        log.info("In progress - counter: [{}] - value: [{}]", getCustomCounter(), counter);
    }

}
