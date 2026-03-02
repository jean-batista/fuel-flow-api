package com.jeanbatista.unittests.mapper;

import com.jeanbatista.data.dto.FuelPumpDto;
import com.jeanbatista.data.dto.FuelTypeDto;
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

        FuelType fuelType = FuelTypeMocks.mockEntity("Etanol", 3.50);
        FuelPump entity = FuelPumpMocks.mockEntity("Bomba 01", fuelType);

        FuelPumpDto dto = FuelPumpMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertNotNull(dto.getFuelType());
        assertEquals(entity.getFuelType().getName(), dto.getFuelType().getName());
    }

    @Test
    @DisplayName("Should map FuelPumpDto to FuelPump entity with nested object")
    void shouldMapDtoToEntityWithNestedObject() {

        FuelTypeDto fuelTypeDto = FuelTypeMocks.mockDto("Gás", 4.00);
        FuelPumpDto dto = FuelPumpMocks.mockDto("Bomba GNV", fuelTypeDto);

        FuelPump entity = FuelPumpMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertNotNull(entity.getFuelType());
        assertEquals(dto.getFuelType().getName(), entity.getFuelType().getName());
    }
}
