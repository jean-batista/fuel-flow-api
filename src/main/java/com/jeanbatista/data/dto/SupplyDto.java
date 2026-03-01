package com.jeanbatista.data.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class SupplyDto implements Serializable {

    private UUID id;
    private FuelPumpDto fuelPump;
    private LocalDateTime supplyDate;
    private BigDecimal totalPrice;
    private BigDecimal liters;

    public SupplyDto() {
    }

    public SupplyDto(UUID id, FuelPumpDto fuelPump, LocalDateTime supplyDate, BigDecimal totalPrice, BigDecimal liters) {
        this.id = id;
        this.fuelPump = fuelPump;
        this.supplyDate = supplyDate;
        this.totalPrice = totalPrice;
        this.liters = liters;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FuelPumpDto getFuelPump() {
        return fuelPump;
    }

    public void setFuelPump(FuelPumpDto fuelPump) {
        this.fuelPump = fuelPump;
    }

    public LocalDateTime getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(LocalDateTime supplyDate) {
        this.supplyDate = supplyDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
        SupplyDto supplyDto = (SupplyDto) o;
        return Objects.equals(id, supplyDto.id) && Objects.equals(fuelPump, supplyDto.fuelPump) && Objects.equals(supplyDate, supplyDto.supplyDate) && Objects.equals(totalPrice, supplyDto.totalPrice) && Objects.equals(liters, supplyDto.liters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fuelPump, supplyDate, totalPrice, liters);
    }
}
