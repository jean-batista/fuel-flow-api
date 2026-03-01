package com.jeanbatista.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "fuel_type")
public class FuelType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Column(name = "price_per_liter")
    private BigDecimal pricePerLiter;

    public FuelType() {
    }

    public FuelType(UUID id, String name, BigDecimal pricePerLiter) {
        this.id = id;
        this.name = name;
        this.pricePerLiter = pricePerLiter;
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

    public BigDecimal getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(BigDecimal pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FuelType fuelType = (FuelType) o;
        return Objects.equals(id, fuelType.id) && Objects.equals(name, fuelType.name) && Objects.equals(pricePerLiter, fuelType.pricePerLiter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pricePerLiter);
    }
}
