package com.jeanbatista.mocks;

import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.model.FuelType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FuelTypeMocks {

    public static FuelType mockEntity(String name, Double pricePerLiter, boolean nullId) {
        return new FuelType(nullId ? null : UUID.randomUUID(), name, new BigDecimal(String.valueOf(pricePerLiter)));
    }

    public static FuelTypeResponseDto mockDto(String name, Double pricePerLiter, boolean nullId) {
        return new FuelTypeResponseDto(nullId ? null : UUID.randomUUID(), name, new BigDecimal(String.valueOf(pricePerLiter)));
    }

    public static List<FuelType> mockListOfEntities(Map<String, Double> fuelTypeMap, boolean nullId) {
        List<FuelType> fuelTypeList = new ArrayList<>();
        for(String fuelTypeName : fuelTypeMap.keySet()) {
            Double pricePerLiter = fuelTypeMap.get(fuelTypeName);
            fuelTypeList.add(new FuelType(
                    nullId ? null : UUID.randomUUID(),
                    fuelTypeName,
                    new BigDecimal(String.valueOf(pricePerLiter))
            ));
        }
        return fuelTypeList;
    }

    public static List<FuelTypeResponseDto> mockListOfDtos(Map<String, Double> fuelTypeDtoMap, boolean nullId) {
        List<FuelTypeResponseDto> fuelTypeResponseDtoList = new ArrayList<>();
        for(String fuelTypeName : fuelTypeDtoMap.keySet()) {
            Double pricePerLiter = fuelTypeDtoMap.get(fuelTypeName);
            fuelTypeResponseDtoList.add(new FuelTypeResponseDto(
                    nullId ? null : UUID.randomUUID(),
                    fuelTypeName,
                    new BigDecimal(String.valueOf(pricePerLiter))
            ));
        }
        return fuelTypeResponseDtoList;
    }

}
