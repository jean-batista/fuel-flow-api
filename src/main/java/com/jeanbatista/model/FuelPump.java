package com.jeanbatista.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "fuel_pump")
public class FuelPump {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id", nullable = false)
    private FuelType fuelType;

    public FuelPump() {
    }

    public FuelPump(UUID id, String name, FuelType fuelType) {
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

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FuelPump fuelPump = (FuelPump) o;
        return Objects.equals(id, fuelPump.id) && Objects.equals(name, fuelPump.name) && Objects.equals(fuelType, fuelPump.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fuelType);
    }
}
