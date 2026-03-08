package com.jeanbatista.mapper;

import com.jeanbatista.data.dto.response.SupplyResponseDto;
import com.jeanbatista.data.dto.request.SupplyRequestDto;
import com.jeanbatista.model.Supply;

public class SupplyMapper {

    public static SupplyResponseDto toDto(Supply entity) {
        return new SupplyResponseDto(
                entity.getId(),
                FuelPumpMapper.toDto(entity.getFuelPump()),
                entity.getSupplyDate(),
                entity.getTotalPrice(),
                entity.getLiters()
        );
    }

    public static Supply toEntity(SupplyResponseDto dto) {
        return new Supply(
                dto.getId(),
                FuelPumpMapper.toEntity(dto.getFuelPump()),
                dto.getSupplyDate(),
                dto.getTotalPrice(),
                dto.getLiters()
        );
    }

    public static Supply toEntity(SupplyRequestDto dto) {
        return new Supply(
                dto.getId(),
                null,
                null,
                null,
                dto.getLiters()
        );
    }

}
