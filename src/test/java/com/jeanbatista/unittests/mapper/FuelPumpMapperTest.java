package com.jeanbatista.unittests.mapper;

import com.jeanbatista.data.dto.request.FuelPumpRequestDto;
import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.mapper.FuelPumpMapper;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelPumpMapperTest {

    @Test
    @DisplayName("Should map FuelPump entity to FuelPumpResponseDto")
    void shouldMapEntityToResponseDto() {

        FuelType fuelType = FuelTypeMocks.mockEntity("Etanol", 3.50, false);
        FuelPump entity = FuelPumpMocks.mockEntity("Bomba 01", fuelType, false);

        FuelPumpResponseDto dto = FuelPumpMapper.toDto(entity);

        assertNotNull(dto);
        assertNotNull(dto.getId());
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertNotNull(dto.getFuelType());
        assertEquals(entity.getFuelType().getName(), dto.getFuelType().getName());
    }

    @Test
    @DisplayName("Should map FuelPumpResponseDto to FuelPump entity")
    void shouldMapResponseDtoToEntity() {

        FuelTypeResponseDto fuelTypeDto = FuelTypeMocks.mockResponseDto("Gás", 4.00, false);
        FuelPumpResponseDto dto = FuelPumpMocks.mockResponseDto("Bomba GNV", fuelTypeDto, false);

        FuelPump entity = FuelPumpMapper.toEntity(dto);

        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertNotNull(entity.getFuelType());
        assertEquals(dto.getFuelType().getName(), entity.getFuelType().getName());
    }

    @Test
    @DisplayName("Should map FuelPumpRequestDto to FuelPump entity")
    void shouldMapRequestDtoToEntity() {
        FuelTypeRequestDto fuelTypeDto = FuelTypeMocks.mockRequestDto("Gás", 4.00, false);
        FuelPumpRequestDto dto = FuelPumpMocks.mockRequestDto("Bomba GNV", fuelTypeDto.getId(), false);

        FuelPump entity = FuelPumpMapper.toEntity(dto);

        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(dto.getId(), entity.getId());
        assertNotNull(entity.getName());
        assertEquals(dto.getName(), entity.getName());
        assertNull(entity.getFuelType());
    }
}
