package com.jeanbatista.services;

import com.jeanbatista.data.dto.FuelPumpDto;
import com.jeanbatista.mapper.FuelPumpMapper;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.repositories.FuelPumpRepository;
import com.jeanbatista.repositories.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuelPumpService {

    @Autowired
    private FuelPumpRepository repository;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    public FuelPumpDto create(FuelPumpDto fuelPumpDto) {
        FuelPump entity = FuelPumpMapper.toEntity(fuelPumpDto);

        if(fuelPumpDto.getFuelType() == null) throw new RuntimeException(
                "O tipo de combustível é obrigatório para esta operação");

        if(fuelPumpDto.getFuelType().getId() == null) throw new RuntimeException(
                "O tipo de combustível não pode possuir um id nulo");

        FuelType fuelType = fuelTypeRepository.findById(fuelPumpDto.getFuelType().getId())
                .orElseThrow(() -> new RuntimeException(
                        "Fuel Type not found with ID: " + fuelPumpDto.getFuelType().getId()
                ));
        entity.setFuelType(fuelType);

        FuelPump savedEntity = repository.save(entity);
        return FuelPumpMapper.toDto(savedEntity);
    }

    public FuelPumpDto findById(UUID id) {
        FuelPump entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Não foi possível encontrar uma bomba de combustível com o id: " + id
                ));
        return FuelPumpMapper.toDto(entity);
    }

    public List<FuelPumpDto> findAll() {
        return repository.findAll().stream()
                .map(FuelPumpMapper::toDto)
                .collect(Collectors.toList());
    }

    public FuelPumpDto update(FuelPumpDto fuelPumpDto) {
        FuelPump entity = repository.findById(fuelPumpDto.getId())
                .orElseThrow(() -> new RuntimeException(
                        "Não foi possível encontrar uma bomba de combustível com o id: " + fuelPumpDto.getId()
                ));

        entity.setName(fuelPumpDto.getName());

        if (fuelPumpDto.getFuelType() != null && fuelPumpDto.getFuelType().getId() != null) {
            FuelType fuelType = fuelTypeRepository.findById(fuelPumpDto.getFuelType().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Fuel Type not found with ID: " + fuelPumpDto.getFuelType().getId()
                    ));
            entity.setFuelType(fuelType);
        }

        FuelPump updatedEntity = repository.save(entity);
        return FuelPumpMapper.toDto(updatedEntity);
    }

    public void delete(UUID id) {
        FuelPump entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Não foi possível encontrar uma bomba de combustível com o id: " + id
                ));
        repository.delete(entity);
    }
}
