package com.devsu.AccountMovementService.application.responses.reporte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Setter
@Getter
public class ReporteEstadoCuenta {

    private String nombre;
    private String identifiacion;
    private String estado;

    private ArrayList<CuentaRepResponse> cuentaRepResponse;

}
