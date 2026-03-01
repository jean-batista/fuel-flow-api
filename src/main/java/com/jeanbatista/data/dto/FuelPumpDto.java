package com.jeanbatista.data.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class FuelPumpDto implements Serializable {

    private UUID id;
    private String name;
    private FuelTypeDto fuelType;

    public FuelPumpDto() {
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

    public FuelTypeDto getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeDto fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FuelPumpDto that = (FuelPumpDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(fuelType, that.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fuelType);
    }
}
