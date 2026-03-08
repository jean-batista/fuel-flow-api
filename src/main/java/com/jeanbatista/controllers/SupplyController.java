package com.jeanbatista.controllers;

import com.jeanbatista.data.dto.response.SupplyResponseDto;
import com.jeanbatista.data.dto.request.SupplyRequestDto;
import com.jeanbatista.services.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/supply/v1")
public class SupplyController {

    @Autowired
    private SupplyService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SupplyResponseDto create(@RequestBody SupplyRequestDto supplyDto) {
        return service.create(supplyDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SupplyResponseDto findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SupplyResponseDto> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
