package com.devsu.AccountMovementService.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaDTO {

    private String numero;
    private String tipo;
    private BigDecimal saldo;
    private String estado;
    private Long idCliente;
}
