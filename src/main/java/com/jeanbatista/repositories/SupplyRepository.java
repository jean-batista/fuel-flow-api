package com.jeanbatista.repositories;

import com.jeanbatista.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplyRepository extends JpaRepository<Supply, UUID> {
}
