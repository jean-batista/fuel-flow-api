package com.jeanbatista.unittests.mapper;

import com.jeanbatista.data.dto.FuelPumpDto;
import com.jeanbatista.data.dto.FuelTypeDto;
import com.jeanbatista.data.dto.SupplyDto;
import com.jeanbatista.mapper.SupplyMapper;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.mocks.SupplyMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.model.Supply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SupplyMapperTest {

    @Test
    @DisplayName("Should map complete Supply entity to SupplyDto")
    void shouldMapCompleteEntityToDto() {

        FuelType fuelType = FuelTypeMocks.mockEntity("Gasolina", 5.00, false);
        FuelPump fuelPump = FuelPumpMocks.mockEntity("Bomba 01", fuelType, false);
        Supply entity = SupplyMocks.mockEntity(fuelPump, 50.00, true);

        SupplyDto dto = SupplyMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getTotalPrice(), dto.getTotalPrice());
        assertNotNull(entity.getFuelPump());
        assertEquals(entity.getFuelPump().getName(), dto.getFuelPump().getName());
    }

    @Test
    @DisplayName("Should map complete SupplyDto to Supply entity")
    void shouldMapCompleteDtoToEntity() {

        FuelTypeDto fuelTypeDto = FuelTypeMocks.mockDto("Gasolina", 5.00, false);
        FuelPumpDto fuelPumpDto = FuelPumpMocks.mockDto("Bomba 01", fuelTypeDto, false);
        SupplyDto dto = SupplyMocks.mockDto(fuelPumpDto, 50.00, true);

        Supply entity = SupplyMapper.toEntity(dto);

        assertNotNull(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getTotalPrice(), entity.getTotalPrice());
        assertNotNull(dto.getFuelPump());
        assertEquals(dto.getFuelPump().getName(), entity.getFuelPump().getName());
    }
}