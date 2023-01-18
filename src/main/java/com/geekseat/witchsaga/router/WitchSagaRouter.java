package com.geekseat.witchsaga.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class WitchSagaRouter {

    @Autowired
    private WitchSagaHandler witchSagaHandler;

    @Bean("villageKillingRouter")
    public RouterFunction<ServerResponse> villageKillingRouter(){
        return RouterFunctions
                .route(POST("/witch-saga/average-killing"), witchSagaHandler::getAverageKilling);

    }


}
