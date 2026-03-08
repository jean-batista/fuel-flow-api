package com.jeanbatista.unittests.mapper;

import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.mapper.FuelTypeMapper;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.model.FuelType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FuelTypeMapperTest {

    @Test
    @DisplayName("Should map FuelType entity to FuelTypeDto")
    void shouldMapEntityToDto() {
        FuelType entity = FuelTypeMocks.mockEntity("Gasolina", 5.89, false);

        FuelTypeResponseDto dto = FuelTypeMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getPricePerLiter(), dto.getPricePerLiter());
    }

    @Test
    @DisplayName("Should map FuelTypeDto to FuelType entity")
    void shouldMapDtoToEntity() {

        FuelTypeResponseDto dto = FuelTypeMocks.mockDto("Diesel", 6.10, false);

        FuelType entity = FuelTypeMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getPricePerLiter(), entity.getPricePerLiter());
    }
}