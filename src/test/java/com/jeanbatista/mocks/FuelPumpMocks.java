package com.jeanbatista.mocks;

import com.jeanbatista.data.dto.request.FuelPumpRequestDto;
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
        UUID id = nullId ? null : UUID.randomUUID();
        return new FuelPump(id, name, fuelType);
    }

    public static FuelPumpResponseDto mockResponseDto(String name, FuelTypeResponseDto fuelTypeResponseDto, boolean nullId) {
        UUID id = nullId ? null : UUID.randomUUID();
        return new FuelPumpResponseDto(id, name, fuelTypeResponseDto);
    }

    public static FuelPumpRequestDto mockRequestDto(String name, UUID fuelTypeId, boolean nullId) {
        UUID id = nullId ? null : UUID.randomUUID();
        return new FuelPumpRequestDto(id, name, fuelTypeId);
    }

    public static List<FuelPump> mockListOfEntities(Map<String, FuelType> fuelPumpMap, boolean nullId) {
        List<FuelPump> fuelPumpList = new ArrayList<>();
        for(String fuelPumpName : fuelPumpMap.keySet()) {
            FuelType fuelType = fuelPumpMap.get(fuelPumpName);
            fuelPumpList.add(mockEntity(fuelPumpName, fuelType, nullId));
        }
        return fuelPumpList;
    }

    public static List<FuelPumpResponseDto> mockListOfResponseDtos(Map<String, FuelTypeResponseDto> fuelPumpMap, boolean nullId) {
        List<FuelPumpResponseDto> fuelPumpList = new ArrayList<>();
        for(String fuelPumpName : fuelPumpMap.keySet()) {
            FuelTypeResponseDto fuelTypeDto = fuelPumpMap.get(fuelPumpName);
            fuelPumpList.add(mockResponseDto(fuelPumpName, fuelTypeDto, nullId));
        }
        return fuelPumpList;
    }

    public static List<FuelPumpRequestDto> mockListOfRequestDtos(Map<String, UUID> fuelPumpMap, boolean nullId) {
        List<FuelPumpRequestDto> fuelPumpList = new ArrayList<>();
        for(String fuelPumpName : fuelPumpMap.keySet()) {
            UUID fuelTypeId = fuelPumpMap.get(fuelPumpName);
            fuelPumpList.add(mockRequestDto(fuelPumpName, fuelTypeId, nullId));
        }
        return fuelPumpList;
    }

}
