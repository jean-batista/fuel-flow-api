package com.jeanbatista.mocks;

import com.jeanbatista.data.dto.FuelTypeDto;
import com.jeanbatista.model.FuelType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FuelTypeMocks {

    public static FuelType mockEntity(String name, Double pricePerLiter) {
        return new FuelType(UUID.randomUUID(), name, new BigDecimal(String.valueOf(pricePerLiter)));
    }

    public static FuelTypeDto mockDto(String name, Double pricePerLiter) {
        return new FuelTypeDto(UUID.randomUUID(), name, new BigDecimal(String.valueOf(pricePerLiter)));
    }

    public static List<FuelType> mockListOfEntities(Map<String, Double> fuelTypeMap) {
        List<FuelType> fuelTypeList = new ArrayList<>();
        for(String fuelTypeName : fuelTypeMap.keySet()) {
            Double pricePerLiter = fuelTypeMap.get(fuelTypeName);
            fuelTypeList.add(new FuelType(
                    UUID.randomUUID(),
                    fuelTypeName,
                    new BigDecimal(String.valueOf(pricePerLiter))
            ));
        }
        return fuelTypeList;
    }

    public static List<FuelTypeDto> mockListOfDtos(Map<String, Double> fuelTypeDtoMap) {
        List<FuelTypeDto> fuelTypeDtoList = new ArrayList<>();
        for(String fuelTypeName : fuelTypeDtoMap.keySet()) {
            Double pricePerLiter = fuelTypeDtoMap.get(fuelTypeName);
            fuelTypeDtoList.add(new FuelTypeDto(
                    UUID.randomUUID(),
                    fuelTypeName,
                    new BigDecimal(String.valueOf(pricePerLiter))
            ));
        }
        return fuelTypeDtoList;
    }

}
