package it.personal.springcustommetrics.processors;

import io.micrometer.core.instrument.MeterRegistry;
import it.personal.springcustommetrics.util.Router;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MetricTwoProcessor extends AbstractMetricProcessor {

    public MetricTwoProcessor(MeterRegistry meterRegistry) {
        super(meterRegistry);
    }

    @Override
    protected String getCustomCounter() {
        log.info("Metric IN: [{}]", Router.TWO.name());
        return Router.TWO.name();
    }

}
