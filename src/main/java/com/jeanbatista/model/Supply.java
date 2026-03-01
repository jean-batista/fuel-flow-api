package com.jeanbatista.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "supply")
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fuel_pump_id", nullable = false)
    private FuelPump fuelPump;

    @Column(name = "supply_date")
    private LocalDateTime supplyDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
    private BigDecimal liters;

    public Supply() {
    }

    public Supply(UUID id, FuelPump fuelPump, LocalDateTime supplyDate, BigDecimal totalPrice, BigDecimal liters) {
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

    public FuelPump getFuelPump() {
        return fuelPump;
    }

    public void setFuelPump(FuelPump fuelPump) {
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
        Supply supply = (Supply) o;
        return Objects.equals(id, supply.id) && Objects.equals(fuelPump, supply.fuelPump) && Objects.equals(supplyDate, supply.supplyDate) && Objects.equals(totalPrice, supply.totalPrice) && Objects.equals(liters, supply.liters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fuelPump, supplyDate, totalPrice, liters);
    }
}
