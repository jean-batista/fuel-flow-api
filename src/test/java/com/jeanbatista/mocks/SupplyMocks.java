package com.jeanbatista.mocks;

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
        BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
        BigDecimal pricePerLiter = fuelPump.getFuelType().getPricePerLiter();
        BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
        return new Supply(nullId ? null : UUID.randomUUID(), fuelPump, LocalDateTime.now(), totalPrice, bigDecimalLiters);
    }

    public static SupplyResponseDto mockDto(FuelPumpResponseDto fuelPumpResponseDto, Double liters, boolean nullId) {
        BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
        BigDecimal pricePerLiter = fuelPumpResponseDto.getFuelType().getPricePerLiter();
        BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
        return new SupplyResponseDto(nullId ? null : UUID.randomUUID(), fuelPumpResponseDto, LocalDateTime.now(), totalPrice, bigDecimalLiters);
    }

    public static List<Supply> mockListOfEntities(Map<FuelPump, Double> supplyMap, boolean nullId) {
        List<Supply> supplyList = new ArrayList<>();
        for(FuelPump fuelPump : supplyMap.keySet()) {
            Double liters = supplyMap.get(fuelPump);
            BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
            BigDecimal pricePerLiter = fuelPump.getFuelType().getPricePerLiter();
            BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
            supplyList.add(new Supply(
                    nullId ? null : UUID.randomUUID(),
                    fuelPump,
                    LocalDateTime.now(),
                    totalPrice,
                    bigDecimalLiters
            ));
        }
        return supplyList;
    }

    public static List<SupplyResponseDto> mockListOfDtos(Map<FuelPumpResponseDto, Double> supplyMap, boolean nullId) {
        List<SupplyResponseDto> supplyResponseDtoList = new ArrayList<>();
        for(FuelPumpResponseDto fuelPumpResponseDto : supplyMap.keySet()) {
            Double liters = supplyMap.get(fuelPumpResponseDto);
            BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
            BigDecimal pricePerLiter = fuelPumpResponseDto.getFuelType().getPricePerLiter();
            BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
            supplyResponseDtoList.add(new SupplyResponseDto(
                    nullId ? null : UUID.randomUUID(),
                    fuelPumpResponseDto,
                    LocalDateTime.now(),
                    totalPrice,
                    bigDecimalLiters
            ));
        }
        return supplyResponseDtoList;
    }

}
