package com.jeanbatista.mocks;

import com.jeanbatista.data.dto.FuelPumpDto;
import com.jeanbatista.data.dto.FuelTypeDto;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FuelPumpMocks {

    public static FuelPump mockEntity(String name, FuelType fuelType) {
        return new FuelPump(UUID.randomUUID(), name, fuelType);
    }

    public static FuelPumpDto mockDto(String name, FuelTypeDto fuelTypeDto) {
        return new FuelPumpDto(UUID.randomUUID(), name, fuelTypeDto);
    }

    public static List<FuelPump> mockListOfEntities(Map<String, FuelType> fuelPumpMap) {
        List<FuelPump> fuelPumpList = new ArrayList<>();
        for(String fuelPumpName : fuelPumpMap.keySet()) {
            FuelType fuelType = fuelPumpMap.get(fuelPumpName);
            fuelPumpList.add(new FuelPump(
                    UUID.randomUUID(),
                    fuelPumpName,
                    fuelType
            ));
        }
        return fuelPumpList;
    }

    public static List<FuelPumpDto> mockListOfDtos(Map<String, FuelTypeDto> fuelPumpMap) {
        List<FuelPumpDto> fuelPumpList = new ArrayList<>();
        for(String fuelPumpName : fuelPumpMap.keySet()) {
            FuelTypeDto fuelTypeDto = fuelPumpMap.get(fuelPumpName);
            fuelPumpList.add(new FuelPumpDto(
                    UUID.randomUUID(),
                    fuelPumpName,
                    fuelTypeDto
            ));
        }
        return fuelPumpList;
    }

}
