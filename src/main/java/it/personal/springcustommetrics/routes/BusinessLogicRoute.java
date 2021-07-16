package it.personal.springcustommetrics.routes;

import it.personal.springcustommetrics.util.RouteConstant;
import it.personal.springcustommetrics.util.Router;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import it.personal.springcustommetrics.util.ProcessorConstant;

@Component
public class BusinessLogicRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .process(ProcessorConstant.EXCEPTION_PROCESSOR);

        from("timer://mytimer?fixedRate=true&period=5000")
                .process(ProcessorConstant.ENRICH_BODY_PROCESSOR)
                .choice()
                    .when(header(ProcessorConstant.ROUTER).isEqualTo(Router.ONE))
                        .to(RouteConstant.VM_OUT_ROUTE_ONE)
                    .when(header(ProcessorConstant.ROUTER).isEqualTo(Router.TWO))
                        .to(RouteConstant.VM_OUT_ROUTE_TWO)
                    .otherwise()
                        .to(RouteConstant.VM_OUT_ROUTE_DEFAULT);

        from(RouteConstant.VM_OUT_ROUTE_ONE)
            .process(ProcessorConstant.METRIC_ONE_PROCESSOR)
            .stop();

        from(RouteConstant.VM_OUT_ROUTE_TWO)
                .process(ProcessorConstant.METRIC_TWO_PROCESSOR)
                .stop();

        from(RouteConstant.VM_OUT_ROUTE_DEFAULT)
                .process(ProcessorConstant.LOGGING_PROCESSOR)
                .stop();

    }

}
