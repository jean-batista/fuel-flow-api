package com.jeanbatista.data.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class FuelTypeRequestDto implements Serializable {

    private UUID id;
    private String name;
    private BigDecimal pricePerLiter;

    public FuelTypeRequestDto() {
    }

    public FuelTypeRequestDto(UUID id, String name, BigDecimal pricePerLiter) {
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
        FuelTypeRequestDto that = (FuelTypeRequestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(pricePerLiter, that.pricePerLiter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pricePerLiter);
    }
}
