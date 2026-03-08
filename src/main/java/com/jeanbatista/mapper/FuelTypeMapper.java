package com.jeanbatista.mapper;

import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.model.FuelType;

public class FuelTypeMapper {

    public static FuelTypeResponseDto toDto(FuelType entity) {
        return new FuelTypeResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getPricePerLiter()
        );
    }

    public static FuelType toEntity(FuelTypeResponseDto dto) {
        return new FuelType(
                dto.getId(),
                dto.getName(),
                dto.getPricePerLiter()
        );
    }

    public static FuelType toEntity(FuelTypeRequestDto dto) {
        return new FuelType(
                dto.getId(),
                dto.getName(),
                dto.getPricePerLiter()
        );
    }

}
