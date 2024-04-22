package com.devsu.AccountMovementService.application.responses;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class MovimientoResponse {

    private Long id;
    private String cuenta;
    private LocalDate fecha;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal saldo;

}
