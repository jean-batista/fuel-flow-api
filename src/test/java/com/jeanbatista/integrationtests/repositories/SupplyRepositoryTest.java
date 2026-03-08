package com.jeanbatista.integrationtests.repositories;

import com.jeanbatista.integrationtests.AbstractIntegrationTest;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.mocks.SupplyMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.model.Supply;
import com.jeanbatista.repositories.FuelPumpRepository;
import com.jeanbatista.repositories.FuelTypeRepository;
import com.jeanbatista.repositories.SupplyRepository;
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
class SupplyRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private FuelPumpRepository fuelPumpRepository;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    private FuelType fuelType;
    private FuelPump fuelPump;
    private Supply supply;

    @BeforeEach
    void setup() {
        fuelType = FuelTypeMocks.mockEntity("Gasolina", 5.89, true);
        fuelPump = FuelPumpMocks.mockEntity("Bomba 01", null, true);
    }

    @Test
    @DisplayName("Should save a Supply entity")
    void shouldSaveASupplyEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        supply = SupplyMocks.mockEntity(savedFuelPump, 20.0, true);
        Supply savedSupply = supplyRepository.save(supply);

        assertNotNull(savedSupply);
        assertNotNull(savedSupply.getId());
        assertNotNull(savedSupply.getFuelPump());
        assertEquals(new BigDecimal("20.0"), savedSupply.getLiters());
        assertEquals(new BigDecimal("117.800"), savedSupply.getTotalPrice());
    }

    @Test
    @DisplayName("Should return a Supply entity by your id")
    void shouldReturnASupplyEntityByYourId() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        supply = SupplyMocks.mockEntity(savedFuelPump, 20.0, true);
        Supply savedSupply = supplyRepository.save(supply);

        Optional<Supply> optional = supplyRepository.findById(savedSupply.getId());
        Supply foundSupply = optional.get();

        assertNotNull(foundSupply);
        assertNotNull(foundSupply.getId());
        assertEquals(savedSupply.getId(), foundSupply.getId());
        assertEquals(new BigDecimal("20.0"), foundSupply.getLiters());
        assertEquals(new BigDecimal("117.800"), foundSupply.getTotalPrice());
    }

    @Test
    @DisplayName("Should return a list of Supply entity")
    void shouldReturnAListOfSupplyEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        List<Supply> suppliesToSave = SupplyMocks.mockListOfEntities(Map.of(
            savedFuelPump, 20.0
        ), true);
        
        FuelPump fuelPump2 = FuelPumpMocks.mockEntity("Bomba 02", savedFuelType, true);
        FuelPump savedFuelPump2 = fuelPumpRepository.save(fuelPump2);
        
        suppliesToSave.add(SupplyMocks.mockEntity(savedFuelPump2, 10.0, true));
        
        supplyRepository.saveAll(suppliesToSave);

        List<Supply> supplyList = supplyRepository.findAll();

        assertNotNull(supplyList);
        assertFalse(supplyList.isEmpty());
        assertTrue(supplyList.size() >= suppliesToSave.size());
    }

    @Test
    @DisplayName("Should update a Supply entity")
    void shouldUpdateASupplyEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        supply = SupplyMocks.mockEntity(savedFuelPump, 20.0, true);
        Supply savedSupply = supplyRepository.save(supply);

        savedSupply.setLiters(new BigDecimal("30.0"));
        savedSupply.setTotalPrice(new BigDecimal("176.700"));
        
        Supply updatedSupply = supplyRepository.save(savedSupply);

        assertNotNull(updatedSupply);
        assertEquals(new BigDecimal("30.0"), updatedSupply.getLiters());
        assertEquals(new BigDecimal("176.700"), updatedSupply.getTotalPrice());
    }

    @Test
    @DisplayName("Should delete a Supply entity")
    void shouldDeleteASupplyEntity() {
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        fuelPump.setFuelType(savedFuelType);
        FuelPump savedFuelPump = fuelPumpRepository.save(fuelPump);

        supply = SupplyMocks.mockEntity(savedFuelPump, 20.0, true);
        Supply savedSupply = supplyRepository.save(supply);

        supplyRepository.delete(savedSupply);

        Optional<Supply> deletedSupply = supplyRepository.findById(savedSupply.getId());
        assertTrue(deletedSupply.isEmpty());
    }
}