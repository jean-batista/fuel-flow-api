package com.jeanbatista.mapper;

import com.jeanbatista.data.dto.FuelTypeDto;
import com.jeanbatista.model.FuelType;

public class FuelTypeMapper {

    public static FuelTypeDto toDto(FuelType entity) {
        return new FuelTypeDto(
                entity.getId(),
                entity.getName(),
                entity.getPricePerLiter()
        );
    }

    public static FuelType toEntity(FuelTypeDto dto) {
        return new FuelType(
                dto.getId(),
                dto.getName(),
                dto.getPricePerLiter()
        );
    }

}
