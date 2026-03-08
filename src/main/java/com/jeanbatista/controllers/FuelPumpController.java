package com.jeanbatista.controllers;

import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.request.FuelPumpRequestDto;
import com.jeanbatista.services.FuelPumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fuel-pump/v1")
public class FuelPumpController {

    @Autowired
    private FuelPumpService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FuelPumpResponseDto create(@RequestBody FuelPumpRequestDto fuelPumpDto) {
        return service.create(fuelPumpDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FuelPumpResponseDto findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FuelPumpResponseDto> findAll() {
        return service.findAll();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FuelPumpResponseDto update(@RequestBody FuelPumpRequestDto fuelPumpDto) {
        return service.update(fuelPumpDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
