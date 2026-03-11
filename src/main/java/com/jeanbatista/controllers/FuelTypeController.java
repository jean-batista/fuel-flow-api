package com.jeanbatista.controllers;

import com.jeanbatista.controllers.docs.FuelTypeControllerDocs;
import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.services.FuelTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fuel-type/v1")
@Tag(name = "Fuel Type", description = "Endpoints for Managing Fuel Types")
public class FuelTypeController implements FuelTypeControllerDocs {

    @Autowired
    private FuelTypeService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public FuelTypeResponseDto create(@RequestBody FuelTypeRequestDto fuelTypeDto) {
        return service.create(fuelTypeDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public FuelTypeResponseDto findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<FuelTypeResponseDto> findAll() {
        return service.findAll();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public FuelTypeResponseDto update(@RequestBody FuelTypeRequestDto fuelTypeDto) {
        return service.update(fuelTypeDto);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
