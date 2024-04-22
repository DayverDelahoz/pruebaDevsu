package com.devsu.ClientPersonaService.domain.repository;

import com.devsu.ClientPersonaService.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
