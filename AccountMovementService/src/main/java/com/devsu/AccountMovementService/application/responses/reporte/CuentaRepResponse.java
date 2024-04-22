package com.devsu.AccountMovementService.application.responses.reporte;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaRepResponse {
    private Long id;
    private String numero;
    private String tipo;
    private BigDecimal saldo;
    private String estado;
    private Long idCliente;
    private ArrayList<MovimientoRepResponse> movimientoResponseList;
}
