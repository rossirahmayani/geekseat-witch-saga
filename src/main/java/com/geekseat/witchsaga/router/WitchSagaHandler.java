package com.geekseat.witchsaga.router;

import com.geekseat.witchsaga.dto.Villager;
import com.geekseat.witchsaga.service.VillageKillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
public class WitchSagaHandler {

    @Autowired
    private VillageKillingService villagerKillingService;


    public Mono<ServerResponse> getAverageKilling (ServerRequest request){
        Mono<BigDecimal> average = request.bodyToFlux(Villager.class)
                .collectList()
                .flatMap(villagers -> {
                    return Mono.just(villagerKillingService.getAverageKillCount(villagers));
                });

        return ServerResponse.ok().body(average, BigDecimal.class);

    }

}
