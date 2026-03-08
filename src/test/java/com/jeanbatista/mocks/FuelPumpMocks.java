package com.jeanbatista.mocks;

import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FuelPumpMocks {

    public static FuelPump mockEntity(String name, FuelType fuelType, boolean nullId) {
        return new FuelPump(nullId ? null : UUID.randomUUID(), name, fuelType);
    }

    public static FuelPumpResponseDto mockDto(String name, FuelTypeResponseDto fuelTypeResponseDto, boolean nullId) {
        return new FuelPumpResponseDto(nullId ? null : UUID.randomUUID(), name, fuelTypeResponseDto);
    }

    public static List<FuelPump> mockListOfEntities(Map<String, FuelType> fuelPumpMap, boolean nullId) {
        List<FuelPump> fuelPumpList = new ArrayList<>();
        for(String fuelPumpName : fuelPumpMap.keySet()) {
            FuelType fuelType = fuelPumpMap.get(fuelPumpName);
            fuelPumpList.add(new FuelPump(
                    nullId ? null : UUID.randomUUID(),
                    fuelPumpName,
                    fuelType
            ));
        }
        return fuelPumpList;
    }

    public static List<FuelPumpResponseDto> mockListOfDtos(Map<String, FuelTypeResponseDto> fuelPumpMap, boolean nullId) {
        List<FuelPumpResponseDto> fuelPumpList = new ArrayList<>();
        for(String fuelPumpName : fuelPumpMap.keySet()) {
            FuelTypeResponseDto fuelTypeResponseDto = fuelPumpMap.get(fuelPumpName);
            fuelPumpList.add(new FuelPumpResponseDto(
                    nullId ? null : UUID.randomUUID(),
                    fuelPumpName,
                    fuelTypeResponseDto
            ));
        }
        return fuelPumpList;
    }

}
