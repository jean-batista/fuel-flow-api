package com.jeanbatista.mapper;

import com.jeanbatista.data.dto.SupplyDto;
import com.jeanbatista.model.Supply;

public class SupplyMapper {

    public static SupplyDto toDto(Supply entity) {
        return new SupplyDto(
                entity.getId(),
                FuelPumpMapper.toDto(entity.getFuelPump()),
                entity.getSupplyDate(),
                entity.getTotalPrice(),
                entity.getLiters()
        );
    }

    public static Supply toEntity(SupplyDto dto) {
        return new Supply(
                dto.getId(),
                FuelPumpMapper.toEntity(dto.getFuelPump()),
                dto.getSupplyDate(),
                dto.getTotalPrice(),
                dto.getLiters()
        );
    }

}
