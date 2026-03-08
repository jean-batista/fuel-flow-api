package com.jeanbatista.unittests.services;

import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.response.SupplyResponseDto;
import com.jeanbatista.exceptions.RequiredObjectIsNullException;
import com.jeanbatista.exceptions.ResourceNotFoundException;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.mocks.SupplyMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.model.Supply;
import com.jeanbatista.repositories.FuelPumpRepository;
import com.jeanbatista.repositories.SupplyRepository;
import com.jeanbatista.services.SupplyService;
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
class SupplyServiceTest {

    @InjectMocks
    private SupplyService service;

    @Mock
    private SupplyRepository supplyRepository;

    @Mock
    private FuelPumpRepository fuelPumpRepository;

    private Supply supply;
    private SupplyResponseDto supplyResponseDto;
    private FuelPump fuelPump;
    private FuelPumpResponseDto fuelPumpResponseDto;
    private FuelType fuelType;

    @BeforeEach
    void setup() {
        fuelType = FuelTypeMocks.mockEntity("Gasolina", 5.89, false);
        fuelPump = FuelPumpMocks.mockEntity("Bomba 01", fuelType, false);
        fuelPumpResponseDto = FuelPumpMocks.mockDto(
                "Bomba 01", FuelTypeMocks.mockDto("Gasolina", 5.89, false), false);
        supply = SupplyMocks.mockEntity(fuelPump, 20.0, false);
        supplyResponseDto = SupplyMocks.mockDto(fuelPumpResponseDto, 20.0, false);
    }

    @Disabled
    @Test
    @DisplayName("Should create a Supply")
    void shouldCreateASupply() {
        when(fuelPumpRepository.findById(fuelPumpResponseDto.getId())).thenReturn(Optional.of(fuelPump));
        when(supplyRepository.save(any(Supply.class))).thenReturn(supply);

//        SupplyResponseDto createdDto = service.create(supplyResponseDto);
//
//        assertNotNull(createdDto);
//        assertNotNull(createdDto.getId());
//        assertEquals(new BigDecimal("20.0"), createdDto.getLiters());
//        assertEquals(new BigDecimal("117.800"), createdDto.getTotalPrice());
    }

    @Test
    @DisplayName("Should throw RequiredObjectIsNullException when creating a null Supply")
    void shouldThrowRequiredObjectIsNullExceptionWhenCreatingANullSupply() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "Não é permitido a persistência de um objeto nulo";
        String actualMessage = exception.getMessage();

        verify(supplyRepository, never()).save(any(Supply.class));
        assertEquals(RequiredObjectIsNullException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Disabled
    @Test
    @DisplayName("Should throw ResourceNotFoundException when creating a Supply with non-existent FuelPump")
    void shouldThrowResourceNotFoundExceptionWhenCreatingASupplyWithNonExistentFuelPump() {
        when(fuelPumpRepository.findById(fuelPumpResponseDto.getId())).thenReturn(Optional.empty());

//        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
//            service.create(supplyResponseDto);
//        });
//
//        String expectedMessage = "Não foi possível encontrar um abastecimento com o id: "
//                + supplyResponseDto.getFuelPump().getId();
//        String actualMessage = exception.getMessage();
//
//        verify(supplyRepository, never()).save(any(Supply.class));
//        assertEquals(ResourceNotFoundException.class, exception.getClass());
//        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should find a Supply by ID")
    void shouldFindASupplyById() {
        when(supplyRepository.findById(supply.getId())).thenReturn(Optional.of(supply));

        SupplyResponseDto foundDto = service.findById(supply.getId());

        assertNotNull(foundDto);
        assertNotNull(foundDto.getId());
        assertEquals(new BigDecimal("20.0"), foundDto.getLiters());
        assertEquals(new BigDecimal("117.800"), foundDto.getTotalPrice());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when finding a non-existent Supply ID")
    void shouldThrowResourceNotFoundExceptionWhenFindingANonExistentSupplyId() {
        UUID nonExistentId = UUID.randomUUID();
        when(supplyRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistentId);
        });

        String expectedMessage = "Não foi possível encontrar um abastecimento com o id: " + nonExistentId;
        String actualMessage = exception.getMessage();

        verify(supplyRepository, times(1)).findById(any(UUID.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Should return a list of Supplies")
    void shouldReturnAListOfSupplies() {
        FuelPump fuelPump02 = FuelPumpMocks.mockEntity("Bomba 02", fuelType, false);
        List<Supply> list = SupplyMocks.mockListOfEntities(
                Map.of(fuelPump, 20.0, fuelPump02, 20.0), false
        );
        when(supplyRepository.findAll()).thenReturn(list);

        List<SupplyResponseDto> dtoList = service.findAll();

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
        assertNotNull(list.get(0).getFuelPump());
        assertNotNull(list.get(0).getFuelPump().getFuelType());
        assertEquals(list.get(0).getSupplyDate(), dtoList.get(0).getSupplyDate());
        assertEquals(list.get(0).getTotalPrice(), dtoList.get(0).getTotalPrice());
        assertEquals(list.get(0).getLiters(), dtoList.get(0).getLiters());
        assertNotNull(list.get(1).getFuelPump());
        assertNotNull(list.get(1).getFuelPump().getFuelType());
        assertEquals(list.get(1).getSupplyDate(), dtoList.get(1).getSupplyDate());
        assertEquals(list.get(1).getTotalPrice(), dtoList.get(1).getTotalPrice());
        assertEquals(list.get(1).getLiters(), dtoList.get(1).getLiters());
    }

    @Test
    @DisplayName("Should delete a Supply")
    void shouldDeleteASupply() {
        when(supplyRepository.findById(supply.getId())).thenReturn(Optional.of(supply));
        doNothing().when(supplyRepository).delete(supply);

        service.delete(supply.getId());

        verify(supplyRepository, times(1)).delete(supply);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting a non-existent Supply ID")
    void shouldThrowResourceNotFoundExceptionWhenDeletingANonExistentSupplyId() {
        UUID nonExistentId = UUID.randomUUID();
        when(supplyRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistentId);
        });

        String expectedMessage = "Não foi possível encontrar um abastecimento com o id: " + nonExistentId;
        String actualMessage = exception.getMessage();

        verify(supplyRepository, never()).delete(any(Supply.class));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
        assertEquals(expectedMessage, actualMessage);
    }
}
