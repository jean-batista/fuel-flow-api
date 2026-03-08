package com.jeanbatista.data.dto.request;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FuelPumpRequestDto implements Serializable {

    private UUID id;
    private String name;
    private UUID fuelTypeId;

    public FuelPumpRequestDto() {
    }

    public FuelPumpRequestDto(UUID id, String name, UUID fuelTypeId) {
        this.id = id;
        this.name = name;
        this.fuelTypeId = fuelTypeId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(UUID fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FuelPumpRequestDto that = (FuelPumpRequestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(fuelTypeId, that.fuelTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fuelTypeId);
    }
}
