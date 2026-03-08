package com.jeanbatista.controllers;

import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.services.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fuel-type/v1")
public class FuelTypeController {

    @Autowired
    private FuelTypeService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FuelTypeResponseDto create(@RequestBody FuelTypeRequestDto fuelTypeDto) {
        return service.create(fuelTypeDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FuelTypeResponseDto findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FuelTypeResponseDto> findAll() {
        return service.findAll();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FuelTypeResponseDto update(@RequestBody FuelTypeRequestDto fuelTypeDto) {
        return service.update(fuelTypeDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
