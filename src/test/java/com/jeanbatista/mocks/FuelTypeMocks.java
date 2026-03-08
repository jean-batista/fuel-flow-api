package com.jeanbatista.mocks;

import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.model.FuelType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FuelTypeMocks {

    public static FuelType mockEntity(String name, Double pricePerLiter, boolean nullId) {
        UUID id = nullId ? null : UUID.randomUUID();
        BigDecimal price = new BigDecimal(String.valueOf(pricePerLiter));
        return new FuelType(id, name, price);
    }

    public static FuelTypeResponseDto mockResponseDto(String name, Double pricePerLiter, boolean nullId) {
        UUID id = nullId ? null : UUID.randomUUID();
        BigDecimal price = new BigDecimal(String.valueOf(pricePerLiter));
        return new FuelTypeResponseDto(id, name, price);
    }

    public static FuelTypeRequestDto mockRequestDto(String name, Double pricePerLiter, boolean nullId) {
        UUID id = nullId ? null : UUID.randomUUID();
        BigDecimal price = new BigDecimal(String.valueOf(pricePerLiter));
        return new FuelTypeRequestDto(id, name, price);
    }

    public static List<FuelType> mockListOfEntities(Map<String, Double> fuelTypeMap, boolean nullId) {
        List<FuelType> fuelTypeList = new ArrayList<>();
        for(String fuelTypeName : fuelTypeMap.keySet()) {
            Double pricePerLiter = fuelTypeMap.get(fuelTypeName);
            fuelTypeList.add(mockEntity(fuelTypeName, pricePerLiter, nullId));
        }
        return fuelTypeList;
    }

    public static List<FuelTypeResponseDto> mockListOfResponseDtos(Map<String, Double> fuelTypeDtoMap, boolean nullId) {
        List<FuelTypeResponseDto> fuelTypeResponseDtoList = new ArrayList<>();
        for(String fuelTypeName : fuelTypeDtoMap.keySet()) {
            Double pricePerLiter = fuelTypeDtoMap.get(fuelTypeName);
            fuelTypeResponseDtoList.add(mockResponseDto(fuelTypeName, pricePerLiter, nullId));
        }
        return fuelTypeResponseDtoList;
    }

    public static List<FuelTypeRequestDto> mockListOfRequestDtos(Map<String, Double> fuelTypeDtoMap, boolean nullId) {
        List<FuelTypeRequestDto> fuelTypeRequestDtoList = new ArrayList<>();
        for(String fuelTypeName : fuelTypeDtoMap.keySet()) {
            Double pricePerLiter = fuelTypeDtoMap.get(fuelTypeName);
            fuelTypeRequestDtoList.add(mockRequestDto(fuelTypeName, pricePerLiter, nullId));
        }
        return fuelTypeRequestDtoList;
    }

}
