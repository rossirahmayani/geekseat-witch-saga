package com.geekseat.witchsaga.service;

import com.geekseat.witchsaga.dto.Villager;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Log4j2
class VillagerKillingServiceTest {

    @InjectMocks
    VillagerKillingServiceImpl villagerKillingService;

    List<Villager> villagers;

    @BeforeEach
    void setup(){
        villagers = new ArrayList<>();
        villagers.add(new Villager(10,12));
        villagers.add(new Villager(13,17));
    }

    @Test
    void getAverageKillCount(){
        BigDecimal average = villagerKillingService.getAverageKillCount(villagers);
        Assertions.assertEquals(BigDecimal.valueOf(4.50).setScale(2), average);
    }

    @Test
    void getAverageKillCount_givenNegativeAgeOfDeath(){
        villagers.add(new Villager(-2, 12));
        BigDecimal average = villagerKillingService.getAverageKillCount(villagers);
        Assertions.assertEquals(BigDecimal.valueOf(-1), average);
    }

    @Test
    void getAverageKillCount_givenNegativeYearOfDeath(){
        villagers.add(new Villager(12, -2));
        BigDecimal average = villagerKillingService.getAverageKillCount(villagers);
        Assertions.assertEquals(BigDecimal.valueOf(-1), average);
    }

    @Test
    void getAverageKillCount_givenBornBeforeWitch(){
        Villager oldVillager = new Villager();
        oldVillager.setAgeOfDeath(30);
        oldVillager.setYearOfDeath(12);

        villagers.add(oldVillager);
        BigDecimal average = villagerKillingService.getAverageKillCount(villagers);
        Assertions.assertEquals(BigDecimal.valueOf(-1), average);
    }

    @Test
    void getAverageKillCount_exception(){
        villagers = null;
        BigDecimal average = villagerKillingService.getAverageKillCount(villagers);
        Assertions.assertEquals(BigDecimal.valueOf(-1), average);
    }

}