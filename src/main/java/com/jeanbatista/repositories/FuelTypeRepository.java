package com.jeanbatista.repositories;

import com.jeanbatista.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuelTypeRepository extends JpaRepository<FuelType, UUID> {
}
