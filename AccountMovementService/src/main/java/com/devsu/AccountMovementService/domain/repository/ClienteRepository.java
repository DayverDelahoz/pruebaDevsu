package com.devsu.AccountMovementService.domain.repository;

import com.devsu.AccountMovementService.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
