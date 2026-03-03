package com.jeanbatista.integrationtests.repositories;

import com.jeanbatista.integrationtests.AbstractIntegrationTest;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.repositories.FuelTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FuelTypeRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    private FuelType fuelType;

    @BeforeEach
    void setup() {
        fuelType = FuelTypeMocks.mockEntity("Gasolina", 5.89, true);
    }

    @Test
    @DisplayName("Should save a FuelType entity")
    void shouldSaveAFuelTypeEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);

        assertNotNull(savedFuelType);
        assertNotNull(savedFuelType.getId());
        assertEquals("Gasolina", savedFuelType.getName());
        assertEquals(new BigDecimal("5.89"), savedFuelType.getPricePerLiter());
    }

    @Test
    @DisplayName("Should return a FuelType entity by your id")
    void shouldReturnAFuelTypeEntityByYourId() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);

        FuelType foundFuelType = fuelTypeRepository.findById(savedFuelType.getId())
                .orElseThrow(() -> new RuntimeException(
                        "Não foi possível encontrar um FuelType com o id " + savedFuelType.getId())
                );

        assertNotNull(foundFuelType);
        assertNotNull(foundFuelType.getId());
        assertEquals("Gasolina", foundFuelType.getName());
        assertEquals(new BigDecimal("5.89"), foundFuelType.getPricePerLiter());
    }

    @Test
    @DisplayName("Should throw exception when FuelType entity not found by id")
    void shouldThrowExceptionWhenFuelTypeEntityNotFoundById() {
        UUID nonExistentId = UUID.randomUUID();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            fuelTypeRepository.findById(nonExistentId)
                    .orElseThrow(() -> new RuntimeException("Não foi possível encontrar um FuelType com o id " + nonExistentId));
        });

        String expectedMessage = "Não foi possível encontrar um FuelType com o id " + nonExistentId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Should return a list of FuelType entity")
    void shouldReturnAListOfFuelTypeEntity() {
        List<FuelType> fuelTypesToSave = FuelTypeMocks.mockListOfEntities(Map.of(
            "Gasolina", 5.89,
            "Etanol", 3.49
        ), true);
        
        fuelTypeRepository.saveAll(fuelTypesToSave);

        List<FuelType> fuelTypeList = fuelTypeRepository.findAll();

        assertNotNull(fuelTypeList);
        assertEquals(2, fuelTypeList.size());
    }

    @Test
    @DisplayName("Should update a FuelType entity")
    void shouldUpdateAFuelTypeEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);

        savedFuelType.setName("Gasolina Aditivada");
        savedFuelType.setPricePerLiter(new BigDecimal("6.09"));
        
        FuelType updatedFuelType = fuelTypeRepository.save(savedFuelType);

        assertNotNull(updatedFuelType);
        assertNotNull(updatedFuelType.getId());
        assertEquals("Gasolina Aditivada", updatedFuelType.getName());
        assertEquals(new BigDecimal("6.09"), updatedFuelType.getPricePerLiter());
    }

    @Test
    @DisplayName("Should delete a FuelType entity")
    void shouldDeleteAFuelTypeEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);

        fuelTypeRepository.delete(savedFuelType);

        Optional<FuelType> deletedFuelType = fuelTypeRepository.findById(savedFuelType.getId());
        assertTrue(deletedFuelType.isEmpty());
    }
}