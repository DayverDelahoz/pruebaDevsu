package com.devsu.AccountMovementService.infraestructure.iservices;

import com.devsu.AccountMovementService.application.responses.reporte.ReporteEstadoCuenta;

import java.time.LocalDate;

public interface IReporteService {
    ReporteEstadoCuenta generarReporteEstadoCuenta(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente);
}
