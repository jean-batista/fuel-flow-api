package com.jeanbatista.repositories;

import com.jeanbatista.model.FuelPump;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuelPumpRepository extends JpaRepository<FuelPump, UUID> {
}
