package com.jeanbatista.mocks;

import com.jeanbatista.data.dto.FuelPumpDto;
import com.jeanbatista.data.dto.SupplyDto;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.Supply;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SupplyMocks {

    public static Supply mockEntity(FuelPump fuelPump, Double liters) {
        BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
        BigDecimal pricePerLiter = fuelPump.getFuelType().getPricePerLiter();
        BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
        return new Supply(UUID.randomUUID(), fuelPump, LocalDateTime.now(), totalPrice, bigDecimalLiters);
    }

    public static SupplyDto mockDto(FuelPumpDto fuelPumpDto, Double liters) {
        BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
        BigDecimal pricePerLiter = fuelPumpDto.getFuelType().getPricePerLiter();
        BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
        return new SupplyDto(UUID.randomUUID(), fuelPumpDto, LocalDateTime.now(), totalPrice, bigDecimalLiters);
    }

    public static List<Supply> mockListOfEntities(Map<FuelPump, Double> supplyMap) {
        List<Supply> supplyList = new ArrayList<>();
        for(FuelPump fuelPump : supplyMap.keySet()) {
            Double liters = supplyMap.get(fuelPump);
            BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
            BigDecimal pricePerLiter = fuelPump.getFuelType().getPricePerLiter();
            BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
            supplyList.add(new Supply(
                    UUID.randomUUID(),
                    fuelPump,
                    LocalDateTime.now(),
                    totalPrice,
                    bigDecimalLiters
            ));
        }
        return supplyList;
    }

    public static List<SupplyDto> mockListOfDtos(Map<FuelPumpDto, Double> supplyMap) {
        List<SupplyDto> supplyDtoList = new ArrayList<>();
        for(FuelPumpDto fuelPumpDto : supplyMap.keySet()) {
            Double liters = supplyMap.get(fuelPumpDto);
            BigDecimal bigDecimalLiters = new BigDecimal(String.valueOf(liters));
            BigDecimal pricePerLiter = fuelPumpDto.getFuelType().getPricePerLiter();
            BigDecimal totalPrice = pricePerLiter.multiply(bigDecimalLiters);
            supplyDtoList.add(new SupplyDto(
                    UUID.randomUUID(),
                    fuelPumpDto,
                    LocalDateTime.now(),
                    totalPrice,
                    bigDecimalLiters
            ));
        }
        return supplyDtoList;
    }

}
