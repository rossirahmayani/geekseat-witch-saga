package com.geekseat.witchsaga.service;

import com.geekseat.witchsaga.dto.Villager;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@Log4j2
public class VillagerKillingServiceImpl implements VillageKillingService{

    @Override
    public BigDecimal getAverageKillCount (List<Villager> villagers){
        if (validateAge(villagers).equals(Boolean.FALSE)){
            return BigDecimal.valueOf(-1);
        }
        BigDecimal totalVillager = BigDecimal.valueOf(villagers.size());
        BigDecimal totalKilled = getTotalKilled(villagers);

        return totalKilled.divide(totalVillager, 2, RoundingMode.HALF_UP);
    }


    private Boolean validateAge(List<Villager> villagers){
        if (villagers.stream().anyMatch(villager -> villager.getAgeOfDeath().compareTo(0) < 0)){
            return Boolean.FALSE;
        }
        if (villagers.stream().anyMatch(villager -> (villager.getYearOfDeath() - villager.getAgeOfDeath()) < 0)){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    private BigDecimal getTotalKilled(List<Villager> villagers){
        Integer totalKilled = 0;

        for (Villager villager: villagers) {
            Integer witchYear = villager.getYearOfDeath() - villager.getAgeOfDeath();
            Integer killCount = countTotalKillInYear(witchYear);
            totalKilled += killCount;
        }

        return BigDecimal.valueOf(totalKilled);
    }

    private Integer countTotalKillInYear(Integer witchYear){
        Integer killCount = 0;

        int a = 1;
        int b = 1;
        int c;

        for(int i = 0; i < witchYear; i++){
            if (i > 1) {
                c = a + b;
                a = b;
                b = c;
                killCount += b;
            }
            else {
                killCount += 1;
            }
        }

        return killCount;
    }

}
