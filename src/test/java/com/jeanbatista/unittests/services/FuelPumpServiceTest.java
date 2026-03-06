package com.jeanbatista.unittests.services;

import com.jeanbatista.data.dto.FuelPumpDto;
import com.jeanbatista.data.dto.FuelTypeDto;
import com.jeanbatista.exceptions.RequiredObjectIsNullException;
import com.jeanbatista.exceptions.ResourceNotFoundException;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.repositories.FuelPumpRepository;
import com.jeanbatista.repositories.FuelTypeRepository;
import com.jeanbatista.services.FuelPumpService;
import org.junit.jupiter.api.BeforeEach;
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
class FuelPumpServiceTest {

    @InjectMocks
    private FuelPumpService service;

    @Mock
    private FuelPumpRepository fuelPumpRepository;

    @Mock
    private FuelTypeRepository fuelTypeRepository;

    private FuelPump fuelPump;
    private FuelPumpDto fuelPumpDto;
    private FuelType fuelType;
    private FuelTypeDto fuelTypeDto;

    @BeforeEach
    void setup() {
        fuelType = FuelTypeMocks.mockEntity("Gasolina", 5.89, false);
        fuelTypeDto = FuelTypeMocks.mockDto("Gasolina", 5.89, false);
        fuelPump = FuelPumpMocks.mockEntity("Bomba 01", fuelType, false);
        fuelPumpDto = FuelPumpMocks.mockDto("Bomba 01", fuelTypeDto, false);
    }

    @Test
    @DisplayName("Should create a FuelPump")
    void shouldCreateAFuelPump() {
        when(fuelTypeRepository.findById(fuelTypeDto.getId())).thenReturn(Optional.of(fuelType));
        when(fuelPumpRepository.save(any(FuelPump.class))).thenReturn(fuelPump);

        FuelPumpDto createdDto = service.create(fuelPumpDto);

        assertNotNull(createdDto);
        assertNotNull(createdDto.getId());
        assertEquals("Bomba 01", createdDto.getName());
        assertNotNull(createdDto.getFuelType());
        assertEquals("Gasolina", createdDto.getFuelType().getName());
    }

    @Test
    @DisplayName("Should throw RequiredObjectIsNullException when creating a null FuelPump")
    void shouldThrowRequiredObjectIsNullExceptionWhenCreatingANullFuelPump() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "Não é permitido a persistência de um objeto nulo";
        String actualMessage = exception.getMessage();

        verify(fuelPumpRepository, never()).save(any(FuelPump.class));
        assertEquals(RequiredObjectIsNullException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when creating a FuelPump with non-existent FuelType")
    void shouldThrowResourceNotFoundExceptionWhenCreatingAFuelPumpWithNonExistentFuelType() {
        when(fuelTypeRepository.findById(fuelTypeDto.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.create(fuelPumpDto);
        });

        String expectedMessage = "Não foi possível encontrar um tipo de combustível com o id: "
                + fuelPumpDto.getFuelType().getId();
        String actualMessage = exception.getMessage();

        verify(fuelPumpRepository, never()).save(any(FuelPump.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should find a FuelPump by ID")
    void shouldFindAFuelPumpById() {
        when(fuelPumpRepository.findById(fuelPump.getId())).thenReturn(Optional.of(fuelPump));

        FuelPumpDto foundDto = service.findById(fuelPump.getId());

        assertNotNull(foundDto);
        assertNotNull(foundDto.getId());
        assertEquals("Bomba 01", foundDto.getName());
        assertNotNull(foundDto.getFuelType());
        assertEquals("Gasolina", foundDto.getFuelType().getName());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when finding a non-existent FuelPump ID")
    void shouldThrowResourceNotFoundExceptionWhenFindingANonExistentFuelPumpId() {
        UUID nonExistentId = UUID.randomUUID();
        when(fuelPumpRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistentId);
        });

        String expectedMessage = "Não foi possível encontrar uma bomba de combustível com o id: " + nonExistentId;
        String actualMessage = exception.getMessage();

        verify(fuelPumpRepository, times(1)).findById(any(UUID.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should return a list of FuelPumps")
    void shouldReturnAListOfFuelPumps() {
        List<FuelPump> list = FuelPumpMocks.mockListOfEntities(
                Map.of("Bomba 01", fuelType, "Bomba 02", fuelType), false
        );
        when(fuelPumpRepository.findAll()).thenReturn(list);

        List<FuelPumpDto> dtoList = service.findAll();

        assertNotNull(dtoList);
        assertEquals(list.size(), dtoList.size());
        assertEquals(list.get(0).getName(), dtoList.get(0).getName());
        assertEquals(list.get(1).getName(), dtoList.get(1).getName());
    }

    @Test
    @DisplayName("Should update a FuelPump")
    void shouldUpdateAFuelPump() {
        when(fuelPumpRepository.findById(fuelPumpDto.getId())).thenReturn(Optional.of(fuelPump));
        when(fuelTypeRepository.findById(fuelTypeDto.getId())).thenReturn(Optional.of(fuelType));
        when(fuelPumpRepository.save(any(FuelPump.class))).thenReturn(fuelPump);

        FuelPumpDto updatedDto = service.update(fuelPumpDto);

        assertNotNull(updatedDto);
        assertNotNull(updatedDto.getId());
        assertEquals("Bomba 01", updatedDto.getName());
        assertNotNull(updatedDto.getFuelType());
        assertEquals("Gasolina", updatedDto.getFuelType().getName());
    }

    @Test
    @DisplayName("Should throw RequiredObjectIsNullException when updating a null FuelPump")
    void shouldThrowRequiredObjectIsNullExceptionWhenUpdatingANullFuelPump() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "Não é permitido a persistência de um objeto nulo";
        String actualMessage = exception.getMessage();

        verify(fuelPumpRepository, never()).save(any(FuelPump.class));
        assertEquals(RequiredObjectIsNullException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when updating a non-existent FuelPump ID")
    void shouldThrowResourceNotFoundExceptionWhenUpdatingANonExistentFuelPumpId() {
        when(fuelPumpRepository.findById(fuelPumpDto.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.update(fuelPumpDto);
        });

        String expectedMessage = "Não foi possível encontrar uma bomba de combustível com o id: " + fuelPumpDto.getId();
        String actualMessage = exception.getMessage();

        verify(fuelPumpRepository, never()).save(any(FuelPump.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when updating a FuelPump with non-existent FuelType")
    void shouldThrowResourceNotFoundExceptionWhenUpdatingAFuelPumpWithNonExistentFuelType() {
        when(fuelPumpRepository.findById(fuelPumpDto.getId())).thenReturn(Optional.of(fuelPump));
        when(fuelTypeRepository.findById(fuelPumpDto.getFuelType().getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.update(fuelPumpDto);
        });

        String expectedMessage = "Não foi possível encontrar um tipo de combustível com o id: "
                + fuelPumpDto.getFuelType().getId();
        String actualMessage = exception.getMessage();

        verify(fuelPumpRepository, never()).save(any(FuelPump.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should delete a FuelPump")
    void shouldDeleteAFuelPump() {
        when(fuelPumpRepository.findById(fuelPump.getId())).thenReturn(Optional.of(fuelPump));
        doNothing().when(fuelPumpRepository).delete(fuelPump);

        service.delete(fuelPump.getId());

        verify(fuelPumpRepository, times(1)).delete(fuelPump);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting a non-existent FuelPump ID")
    void shouldThrowResourceNotFoundExceptionWhenDeletingANonExistentFuelPumpId() {
        UUID nonExistentId = UUID.randomUUID();
        when(fuelPumpRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistentId);
        });

        String expectedMessage = "Não foi possível encontrar uma bomba de combustível com o id: " + nonExistentId;
        String actualMessage = exception.getMessage();

        verify(fuelPumpRepository, never()).delete(any(FuelPump.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }
}
