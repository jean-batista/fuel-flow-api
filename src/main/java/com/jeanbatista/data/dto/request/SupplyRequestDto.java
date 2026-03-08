package com.jeanbatista.data.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class SupplyRequestDto implements Serializable {

    private UUID id;
    private UUID fuelPumpId;
    private BigDecimal liters;

    public SupplyRequestDto() {
    }

    public SupplyRequestDto(UUID id, UUID fuelPumpId, BigDecimal liters) {
        this.id = id;
        this.fuelPumpId = fuelPumpId;
        this.liters = liters;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getFuelPumpId() {
        return fuelPumpId;
    }

    public void setFuelPumpId(UUID fuelPumpId) {
        this.fuelPumpId = fuelPumpId;
    }

    public BigDecimal getLiters() {
        return liters;
    }

    public void setLiters(BigDecimal liters) {
        this.liters = liters;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SupplyRequestDto that = (SupplyRequestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(fuelPumpId, that.fuelPumpId) && Objects.equals(liters, that.liters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fuelPumpId, liters);
    }
}
