package com.jeanbatista.integrationtests.repositories;

import com.jeanbatista.integrationtests.AbstractIntegrationTest;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.repositories.FuelPumpRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FuelPumpRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private FuelPumpRepository fuelPumpRepository;
    private FuelType fuelType;
    private FuelPump fuelPump;

    @BeforeEach
    void setup() {
        fuelType = FuelTypeMocks.mockEntity("Etanol", 3.50, true);
        fuelPump = FuelPumpMocks.mockEntity("Bomba 01", null, true);
    }

    @Test
    @DisplayName("Should save a FuelPump entity")
    void shouldSaveAFuelPumpEntity() {

        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        assertNotNull(savedFuelPump);
        assertNotNull(savedFuelPump.getId());
        assertNotNull(savedFuelPump.getFuelType());
        assertEquals("Bomba 01", savedFuelPump.getName());
        assertEquals("Etanol", savedFuelPump.getFuelType().getName());
        assertEquals(new BigDecimal("3.5"), savedFuelPump.getFuelType().getPricePerLiter());
    }

    @Test
    @DisplayName("Should return a FuelPump entity by your id")
    void shouldReturnAFuelPumpEntityByYourId() {

        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        Optional<FuelPump> optional = fuelPumpRepository.findById(savedFuelPump.getId());
        FuelPump findedFuelPump = optional.get();

        assertNotNull(findedFuelPump);
        assertNotNull(findedFuelPump.getId());
        assertNotNull(findedFuelPump.getFuelType());
        assertEquals("Bomba 01", findedFuelPump.getName());
        assertEquals("Etanol", findedFuelPump.getFuelType().getName());
        assertEquals(new BigDecimal("3.5"), findedFuelPump.getFuelType().getPricePerLiter());
    }

    @Test
    @DisplayName("Should return a list of FuelPump entity")
    void shouldReturnAListOfFuelPumpEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        
        List<FuelPump> fuelPumpsToSave = FuelPumpMocks.mockListOfEntities(Map.of(
            "Bomba 01", savedFuelType,
            "Bomba 02", savedFuelType
        ), true);
        
        fuelPumpRepository.saveAll(fuelPumpsToSave);

        List<FuelPump> fuelPumpList = fuelPumpRepository.findAll();

        assertNotNull(fuelPumpList);
        assertEquals(2, fuelPumpList.size());
    }

    @Test
    @DisplayName("Should update a FuelPump entity")
    void shouldUpdateAFuelPumpEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        savedFuelPump.setName("Bomba 01 Atualizada");
        FuelPump updatedFuelPump = fuelPumpRepository.save(savedFuelPump);

        assertNotNull(updatedFuelPump);
        assertNotNull(updatedFuelPump.getId());
        assertNotNull(updatedFuelPump.getFuelType());
        assertEquals("Bomba 01 Atualizada", updatedFuelPump.getName());
        assertEquals("Etanol", updatedFuelPump.getFuelType().getName());
        assertEquals(new BigDecimal("3.5"), updatedFuelPump.getFuelType().getPricePerLiter());
    }

    @Test
    @DisplayName("Should delete a FuelPump entity")
    void shouldDeleteAFuelPumpEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        fuelPumpRepository.delete(savedFuelPump);

        Optional<FuelPump> deletedFuelPump = fuelPumpRepository.findById(savedFuelPump.getId());
        assertTrue(deletedFuelPump.isEmpty());
    }

}