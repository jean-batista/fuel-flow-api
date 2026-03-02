package com.jeanbatista.mapper;

import com.jeanbatista.data.dto.FuelPumpDto;
import com.jeanbatista.model.FuelPump;

public class FuelPumpMapper {

    public static FuelPumpDto toDto(FuelPump entity) {
        return new FuelPumpDto(
                entity.getId(),
                entity.getName(),
                FuelTypeMapper.toDto(entity.getFuelType())
        );
    }

    public static FuelPump toEntity(FuelPumpDto dto) {
        return new FuelPump(
                dto.getId(),
                dto.getName(),
                FuelTypeMapper.toEntity(dto.getFuelType())
        );
    }
}
