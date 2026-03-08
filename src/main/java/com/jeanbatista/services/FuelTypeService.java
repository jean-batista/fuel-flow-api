package com.jeanbatista.services;

import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.exceptions.RequiredObjectIsNullException;
import com.jeanbatista.exceptions.ResourceNotFoundException;
import com.jeanbatista.mapper.FuelTypeMapper;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.repositories.FuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuelTypeService {

    @Autowired
    private FuelTypeRepository repository;

    public FuelTypeResponseDto create(FuelTypeRequestDto fuelTypeDto) {
        if (fuelTypeDto == null) throw new RequiredObjectIsNullException();

        FuelType entity = FuelTypeMapper.toEntity(fuelTypeDto);
        FuelType savedEntity = repository.save(entity);
        return FuelTypeMapper.toDto(savedEntity);
    }

    public FuelTypeResponseDto findById(UUID id) {
        FuelType entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível encontrar um tipo de combustível com o id: " + id
                ));
        return FuelTypeMapper.toDto(entity);
    }

    public List<FuelTypeResponseDto> findAll() {
        return repository.findAll().stream()
                .map(FuelTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    public FuelTypeResponseDto update(FuelTypeRequestDto fuelTypeDto) {
        if (fuelTypeDto == null) throw new RequiredObjectIsNullException();

        FuelType entity = repository.findById(fuelTypeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível encontrar um tipo de combustível com o id: "
                                + fuelTypeDto.getId()
                ));

        entity.setName(fuelTypeDto.getName());
        entity.setPricePerLiter(fuelTypeDto.getPricePerLiter());

        FuelType updatedEntity = repository.save(entity);
        return FuelTypeMapper.toDto(updatedEntity);
    }

    public void delete(UUID id) {
        FuelType entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível encontrar um tipo de combustível com o id: " + id
                ));
        repository.delete(entity);
    }
}
