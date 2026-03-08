package com.jeanbatista.data.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class SupplyResponseDto implements Serializable {

    private UUID id;
    private FuelPumpResponseDto fuelPump;
    private LocalDateTime supplyDate;
    private BigDecimal totalPrice;
    private BigDecimal liters;

    public SupplyResponseDto() {
    }

    public SupplyResponseDto(UUID id, FuelPumpResponseDto fuelPump, LocalDateTime supplyDate, BigDecimal totalPrice, BigDecimal liters) {
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

    public FuelPumpResponseDto getFuelPump() {
        return fuelPump;
    }

    public void setFuelPump(FuelPumpResponseDto fuelPump) {
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
        SupplyResponseDto supplyResponseDto = (SupplyResponseDto) o;
        return Objects.equals(id, supplyResponseDto.id) && Objects.equals(fuelPump, supplyResponseDto.fuelPump) && Objects.equals(supplyDate, supplyResponseDto.supplyDate) && Objects.equals(totalPrice, supplyResponseDto.totalPrice) && Objects.equals(liters, supplyResponseDto.liters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fuelPump, supplyDate, totalPrice, liters);
    }
}
