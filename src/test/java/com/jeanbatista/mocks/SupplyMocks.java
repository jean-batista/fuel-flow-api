package com.jeanbatista.mocks;

import com.jeanbatista.data.dto.request.SupplyRequestDto;
import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.response.SupplyResponseDto;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.Supply;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SupplyMocks {

    public static Supply mockEntity(FuelPump fuelPump, Double liters, boolean nullId) {
        UUID id = nullId ? null : UUID.randomUUID();
        BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
        BigDecimal pricePerLiter = fuelPump.getFuelType().getPricePerLiter();
        BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
        return new Supply(id, fuelPump, LocalDateTime.now(), totalPrice, bigDecimalLiters);
    }

    public static SupplyResponseDto mockResponseDto(FuelPumpResponseDto fuelPumpResponseDto, Double liters, boolean nullId) {
        UUID id = nullId ? null : UUID.randomUUID();
        BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
        BigDecimal pricePerLiter = fuelPumpResponseDto.getFuelType().getPricePerLiter();
        BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
        return new SupplyResponseDto(id, fuelPumpResponseDto, LocalDateTime.now(), totalPrice, bigDecimalLiters);
    }

    public static SupplyRequestDto mockRequestDto(UUID fuelPumpId, Double liters, boolean nullId) {
        UUID id = nullId ? null : UUID.randomUUID();
        BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
        return new SupplyRequestDto(id, fuelPumpId, bigDecimalLiters);
    }

    public static List<Supply> mockListOfEntities(Map<FuelPump, Double> supplyMap, boolean nullId) {
        List<Supply> supplyList = new ArrayList<>();
        for(FuelPump fuelPump : supplyMap.keySet()) {
            Double liters = supplyMap.get(fuelPump);
            supplyList.add(mockEntity(fuelPump, liters, nullId));
        }
        return supplyList;
    }

    public static List<SupplyResponseDto> mockListOfResponseDtos(Map<FuelPumpResponseDto, Double> supplyMap, boolean nullId) {
        List<SupplyResponseDto> supplyList = new ArrayList<>();
        for(FuelPumpResponseDto fuelPumpDto : supplyMap.keySet()) {
            Double liters = supplyMap.get(fuelPumpDto);
            supplyList.add(mockResponseDto(fuelPumpDto, liters, nullId));
        }
        return supplyList;
    }

    public static List<SupplyRequestDto> mockListOfRequestDtos(Map<UUID, Double> supplyMap, boolean nullId) {
        List<SupplyRequestDto> supplyList = new ArrayList<>();
        for(UUID fuelPumpId : supplyMap.keySet()) {
            Double liters = supplyMap.get(fuelPumpId);
            supplyList.add(mockRequestDto(fuelPumpId, liters, nullId));
        }
        return supplyList;
    }

}
