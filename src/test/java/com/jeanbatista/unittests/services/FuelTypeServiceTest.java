package com.jeanbatista.unittests.services;

import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.exceptions.RequiredObjectIsNullException;
import com.jeanbatista.exceptions.ResourceNotFoundException;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.repositories.FuelTypeRepository;
import com.jeanbatista.services.FuelTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuelTypeServiceTest {

    @InjectMocks
    private FuelTypeService service;

    @Mock
    private FuelTypeRepository fuelTypeRepository;

    private FuelType fuelType;
    private FuelTypeResponseDto fuelTypeResponseDto;

    @BeforeEach
    void setup() {
        fuelType = FuelTypeMocks.mockEntity("Gasolina", 5.89, false);
        fuelTypeResponseDto = FuelTypeMocks.mockDto("Gasolina", 5.89, false);
    }

    @Disabled
    @Test
    @DisplayName("Should create a FuelType")
    void shouldCreateAFuelType() {
        when(fuelTypeRepository.save(any(FuelType.class))).thenReturn(fuelType);

//        FuelTypeResponseDto createdDto = service.create(fuelTypeResponseDto);
//
//        assertNotNull(createdDto);
//        assertNotNull(createdDto.getId());
//        assertEquals("Gasolina", createdDto.getName());
//        assertEquals(new BigDecimal("5.89"), createdDto.getPricePerLiter());
    }

    @Test
    @DisplayName("Should throw RequiredObjectIsNullException when creating a null FuelType")
    void shouldThrowRequiredObjectIsNullExceptionWhenCreatingANullFuelType() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "Não é permitido a persistência de um objeto nulo";
        String actualMessage = exception.getMessage();

        verify(fuelTypeRepository, never()).save(any(FuelType.class));
        assertEquals(RequiredObjectIsNullException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should find a FuelType by ID")
    void shouldFindAFuelTypeById() {
        when(fuelTypeRepository.findById(fuelType.getId())).thenReturn(Optional.of(fuelType));

        FuelTypeResponseDto foundDto = service.findById(fuelType.getId());

        assertNotNull(foundDto);
        assertNotNull(foundDto.getId());
        assertEquals("Gasolina", foundDto.getName());
        assertEquals(new BigDecimal("5.89"), foundDto.getPricePerLiter());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when finding a non-existent FuelType ID")
    void shouldThrowResourceNotFoundExceptionWhenFindingANonExistentFuelTypeId() {
        UUID nonExistentId = UUID.randomUUID();
        when(fuelTypeRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistentId);
        });

        String expectedMessage = "Não foi possível encontrar um tipo de combustível com o id: " + nonExistentId;
        String actualMessage = exception.getMessage();

        verify(fuelTypeRepository, times(1)).findById(any(UUID.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should return a list of FuelTypes")
    void shouldReturnAListOfFuelTypes() {
        List<FuelType> list = FuelTypeMocks.mockListOfEntities(
                Map.of("Gasolina", 5.89, "Diesel", 4.85), false
        );
        when(fuelTypeRepository.findAll()).thenReturn(list);

        List<FuelTypeResponseDto> dtoList = service.findAll();

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertEquals(list.get(0).getName(), dtoList.get(0).getName());
        assertEquals(list.get(0).getPricePerLiter(), dtoList.get(0).getPricePerLiter());
        assertEquals(list.get(1).getName(), dtoList.get(1).getName());
        assertEquals(list.get(1).getPricePerLiter(), dtoList.get(1).getPricePerLiter());
    }

    @Disabled
    @Test
    @DisplayName("Should update a FuelType")
    void shouldUpdateAFuelType() {
        when(fuelTypeRepository.findById(fuelTypeResponseDto.getId())).thenReturn(Optional.of(fuelType));
        when(fuelTypeRepository.save(any(FuelType.class))).thenReturn(fuelType);

//        FuelTypeResponseDto updatedDto = service.update(fuelTypeResponseDto);
//
//        assertNotNull(updatedDto);
//        assertNotNull(updatedDto.getId());
//        assertEquals("Gasolina", updatedDto.getName());
//        assertEquals(new BigDecimal("5.89"), updatedDto.getPricePerLiter());
    }

    @Test
    @DisplayName("Should throw RequiredObjectIsNullException when updating a null FuelType")
    void shouldThrowRequiredObjectIsNullExceptionWhenUpdatingANullFuelType() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "Não é permitido a persistência de um objeto nulo";
        String actualMessage = exception.getMessage();

        verify(fuelTypeRepository, never()).save(any(FuelType.class));
        assertEquals(RequiredObjectIsNullException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Disabled
    @Test
    @DisplayName("Should throw ResourceNotFoundException when updating a non-existent FuelType ID")
    void shouldThrowResourceNotFoundExceptionWhenUpdatingANonExistentFuelTypeId() {
        when(fuelTypeRepository.findById(fuelTypeResponseDto.getId())).thenReturn(Optional.empty());

//        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
//            service.update(fuelTypeResponseDto);
//        });
//
//        String expectedMessage = "Não foi possível encontrar um tipo de combustível com o id: "
//                + fuelTypeResponseDto.getId();
//        String actualMessage = exception.getMessage();
//
//        verify(fuelTypeRepository, never()).save(any(FuelType.class));
//        assertEquals(ResourceNotFoundException.class, exception.getClass());
//        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should delete a FuelType")
    void shouldDeleteAFuelType() {
        when(fuelTypeRepository.findById(fuelType.getId())).thenReturn(Optional.of(fuelType));
        doNothing().when(fuelTypeRepository).delete(fuelType);

        service.delete(fuelType.getId());

        verify(fuelTypeRepository, times(1)).delete(fuelType);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting a non-existent FuelType ID")
    void shouldThrowResourceNotFoundExceptionWhenDeletingANonExistentFuelTypeId() {
        UUID nonExistentId = UUID.randomUUID();
        when(fuelTypeRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistentId);
        });

        String expectedMessage = "Não foi possível encontrar um tipo de combustível com o id: " + nonExistentId;
        String actualMessage = exception.getMessage();

        verify(fuelTypeRepository, never()).delete(any(FuelType.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }
}
