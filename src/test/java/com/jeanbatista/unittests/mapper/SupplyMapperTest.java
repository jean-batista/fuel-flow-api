package com.jeanbatista.unittests.mapper;

import com.jeanbatista.data.dto.request.FuelPumpRequestDto;
import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.data.dto.request.SupplyRequestDto;
import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.data.dto.response.SupplyResponseDto;
import com.jeanbatista.mapper.SupplyMapper;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.mocks.SupplyMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.model.Supply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplyMapperTest {

    @Test
    @DisplayName("Should map Supply entity to SupplyResponseDto")
    void shouldMapEntityToResponseDto() {

        FuelType fuelType = FuelTypeMocks.mockEntity("Gasolina", 5.00, false);
        FuelPump fuelPump = FuelPumpMocks.mockEntity("Bomba 01", fuelType, false);
        Supply entity = SupplyMocks.mockEntity(fuelPump, 50.00, false);

        SupplyResponseDto dto = SupplyMapper.toDto(entity);

        assertNotNull(dto);
        assertNotNull(dto.getId());
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getTotalPrice(), dto.getTotalPrice());
        assertNotNull(entity.getFuelPump());
        assertEquals(entity.getFuelPump().getName(), dto.getFuelPump().getName());
    }

    @Test
    @DisplayName("Should map SupplyResponseDto to Supply entity")
    void shouldMapResponseDtoToEntity() {

        FuelTypeResponseDto fuelTypeResponseDto = FuelTypeMocks.mockResponseDto("Gasolina", 5.00, false);
        FuelPumpResponseDto fuelPumpResponseDto = FuelPumpMocks.mockResponseDto("Bomba 01", fuelTypeResponseDto, false);
        SupplyResponseDto dto = SupplyMocks.mockResponseDto(fuelPumpResponseDto, 50.00, false);

        Supply entity = SupplyMapper.toEntity(dto);

        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getTotalPrice(), entity.getTotalPrice());
        assertNotNull(entity.getFuelPump());
        assertEquals(dto.getFuelPump().getName(), entity.getFuelPump().getName());
    }

    @Test
    @DisplayName("Should map SupplyRequestDto to Supply entity")
    void shouldMapRequestDtoToEntity() {

        FuelTypeRequestDto fuelTypeDto = FuelTypeMocks.mockRequestDto("Gasolina", 5.00, false);
        FuelPumpRequestDto fuelPumpDto = FuelPumpMocks.mockRequestDto("Bomba 01", fuelTypeDto.getId(), false);
        SupplyRequestDto dto = SupplyMocks.mockRequestDto(fuelPumpDto.getId(), 50.00, false);

        Supply entity = SupplyMapper.toEntity(dto);

        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(dto.getId(), entity.getId());
        assertNull(entity.getFuelPump());
        assertNull(entity.getSupplyDate());
        assertNull(entity.getSupplyDate());
        assertNotNull(entity.getLiters());
        assertEquals(dto.getLiters(), entity.getLiters());
    }
}