package com.jeanbatista.data.dto.response;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class FuelPumpResponseDto implements Serializable {

    private UUID id;
    private String name;
    private FuelTypeResponseDto fuelType;

    public FuelPumpResponseDto() {
    }

    public FuelPumpResponseDto(UUID id, String name, FuelTypeResponseDto fuelType) {
        this.id = id;
        this.name = name;
        this.fuelType = fuelType;
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

    public FuelTypeResponseDto getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeResponseDto fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FuelPumpResponseDto that = (FuelPumpResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(fuelType, that.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fuelType);
    }
}
