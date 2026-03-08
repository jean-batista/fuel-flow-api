package com.jeanbatista.unittests.mapper;

import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.mapper.FuelPumpMapper;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FuelPumpMapperTest {

    @Test
    @DisplayName("Should map FuelPump entity to FuelPumpDto with nested object")
    void shouldMapEntityToDtoWithNestedObject() {

        FuelType fuelType = FuelTypeMocks.mockEntity("Etanol", 3.50, false);
        FuelPump entity = FuelPumpMocks.mockEntity("Bomba 01", fuelType, false);

        FuelPumpResponseDto dto = FuelPumpMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertNotNull(dto.getFuelType());
        assertEquals(entity.getFuelType().getName(), dto.getFuelType().getName());
    }

    @Test
    @DisplayName("Should map FuelPumpDto to FuelPump entity with nested object")
    void shouldMapDtoToEntityWithNestedObject() {

        FuelTypeResponseDto fuelTypeResponseDto = FuelTypeMocks.mockDto("Gás", 4.00, false);
        FuelPumpResponseDto dto = FuelPumpMocks.mockDto("Bomba GNV", fuelTypeResponseDto, false);

        FuelPump entity = FuelPumpMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertNotNull(entity.getFuelType());
        assertEquals(dto.getFuelType().getName(), entity.getFuelType().getName());
    }
}
