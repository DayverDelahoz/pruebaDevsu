package com.devsu.AccountMovementService.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Setter
@Getter
public class MovimientoRequest {

    private String cuenta;
    private LocalDate fecha;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal saldo;

}
