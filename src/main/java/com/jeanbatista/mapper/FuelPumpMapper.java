package com.jeanbatista.mapper;

import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.request.FuelPumpRequestDto;
import com.jeanbatista.model.FuelPump;

public class FuelPumpMapper {

    public static FuelPumpResponseDto toDto(FuelPump entity) {
        return new FuelPumpResponseDto(
                entity.getId(),
                entity.getName(),
                FuelTypeMapper.toDto(entity.getFuelType())
        );
    }

    public static FuelPump toEntity(FuelPumpResponseDto dto) {
        return new FuelPump(
                dto.getId(),
                dto.getName(),
                FuelTypeMapper.toEntity(dto.getFuelType())
        );
    }

    public static FuelPump toEntity(FuelPumpRequestDto dto) {
        return new FuelPump(
                dto.getId(),
                dto.getName(),
                null
        );
    }
}
