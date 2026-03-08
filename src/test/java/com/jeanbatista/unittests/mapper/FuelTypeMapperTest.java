package com.jeanbatista.unittests.mapper;

import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
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
    @DisplayName("Should map FuelType entity to FuelTypeResponseDto")
    void shouldMapEntityToResponseDto() {
        FuelType entity = FuelTypeMocks.mockEntity("Gasolina", 5.89, false);

        FuelTypeResponseDto dto = FuelTypeMapper.toDto(entity);

        assertNotNull(dto);
        assertNotNull(dto.getId());
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getPricePerLiter(), dto.getPricePerLiter());
    }

    @Test
    @DisplayName("Should map FuelTypeResponseDto to FuelType entity")
    void shouldMapResponseDtoToEntity() {
        FuelTypeResponseDto dto = FuelTypeMocks.mockResponseDto("Diesel", 6.10, false);

        FuelType entity = FuelTypeMapper.toEntity(dto);

        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getPricePerLiter(), entity.getPricePerLiter());
    }

    @Test
    @DisplayName("Should map FuelTypeRequestDto to FuelType entity")
    void shouldMapRequestDtoToEntity() {
        FuelTypeRequestDto dto = FuelTypeMocks.mockRequestDto("Etanol", 3.5, false);

        FuelType entity = FuelTypeMapper.toEntity(dto);

        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getPricePerLiter(), entity.getPricePerLiter());
    }
}