package com.devsu.ClientPersonaService.domain.repository;

import com.devsu.ClientPersonaService.domain.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
