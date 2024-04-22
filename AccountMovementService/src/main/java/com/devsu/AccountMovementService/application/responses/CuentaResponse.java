package com.devsu.AccountMovementService.application.responses;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaResponse {
    private Long id;
    private String numero;
    private String tipo;
    private BigDecimal saldo;
    private String estado;
    private Long idCliente;
}
