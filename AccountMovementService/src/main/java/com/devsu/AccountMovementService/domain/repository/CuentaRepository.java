package com.devsu.AccountMovementService.domain.repository;

import com.devsu.AccountMovementService.domain.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> findByNumero(String nro);
}
