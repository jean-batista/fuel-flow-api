package com.jeanbatista.services;

import com.jeanbatista.data.dto.SupplyDto;
import com.jeanbatista.exceptions.RequiredObjectIsNullException;
import com.jeanbatista.exceptions.ResourceNotFoundException;
import com.jeanbatista.mapper.SupplyMapper;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.Supply;
import com.jeanbatista.repositories.FuelPumpRepository;
import com.jeanbatista.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplyService {

    @Autowired
    private SupplyRepository repository;

    @Autowired
    private FuelPumpRepository fuelPumpRepository;

    public SupplyDto create(SupplyDto supplyDto) {
        if (supplyDto == null) throw new RequiredObjectIsNullException();

        Supply entity = SupplyMapper.toEntity(supplyDto);

        FuelPump fuelPump = fuelPumpRepository.findById(supplyDto.getFuelPump().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível encontrar um abastecimento com o id: "
                                + supplyDto.getFuelPump().getId()
                ));
        
        entity.setFuelPump(fuelPump);
        entity.setSupplyDate(LocalDateTime.now());
        
        BigDecimal pricePerLiter = fuelPump.getFuelType().getPricePerLiter();
        BigDecimal liters = supplyDto.getLiters();
        BigDecimal totalPrice = pricePerLiter.multiply(liters);
        
        entity.setTotalPrice(totalPrice);

        Supply savedEntity = repository.save(entity);
        return SupplyMapper.toDto(savedEntity);
    }

    public SupplyDto findById(UUID id) {
        Supply entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível encontrar um abastecimento com o id: " + id
                ));
        return SupplyMapper.toDto(entity);
    }

    public List<SupplyDto> findAll() {
        return repository.findAll().stream()
                .map(SupplyMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(UUID id) {
        Supply entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível encontrar um abastecimento com o id: " + id
                ));
        repository.delete(entity);
    }
}
