package com.devsu.AccountMovementService.infraestructure.api;

import com.devsu.AccountMovementService.application.responses.reporte.ReporteEstadoCuenta;
import com.devsu.AccountMovementService.infraestructure.iservices.IReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ReporteController {

    private final IReporteService reporteService;

    public ReporteController(IReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @Operation(summary = "Generar un reporte de estado de cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/reportes")
    public ResponseEntity<ReporteEstadoCuenta> generarReporteEstadoCuenta(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam("cliente") Long idCliente) {
        ReporteEstadoCuenta reporte = reporteService.generarReporteEstadoCuenta(fechaInicio, fechaFin, idCliente);
        return ResponseEntity.ok(reporte);
    }
}
