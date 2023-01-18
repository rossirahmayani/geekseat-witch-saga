package com.geekseat.witchsaga.service;

import com.geekseat.witchsaga.dto.Villager;

import java.math.BigDecimal;
import java.util.List;

public interface VillageKillingService {
    BigDecimal getAverageKillCount (List<Villager> villagers);
}
