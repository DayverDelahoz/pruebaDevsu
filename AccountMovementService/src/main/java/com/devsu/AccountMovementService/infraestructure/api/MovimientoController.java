package com.devsu.AccountMovementService.infraestructure.api;

import com.devsu.AccountMovementService.application.dto.MovimientoDTO;
import com.devsu.AccountMovementService.application.dto.MovimientoRequest;
import com.devsu.AccountMovementService.application.responses.MovimientoResponse;
import com.devsu.AccountMovementService.infraestructure.iservices.IMovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final IMovimientoService movimientoService;

    public MovimientoController(IMovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @Operation(summary = "Consulta de todos los movimientos de una cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Servicio no disponible")
    })
    @GetMapping("/cuenta/{nroCuenta}")
    public ResponseEntity<List<MovimientoResponse>> getAllMovimientosByCuentaId(@Parameter(description = "Numero de la cuenta", required = true) @PathVariable String nroCuenta) {
        var movimientos = movimientoService.getAllMovimientosByCuenta(nroCuenta);
        return ResponseEntity.status(HttpStatus.OK).body(movimientos);
    }

    @Operation(summary = "Consulta un movimiento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponse> getMovimientoById(@PathVariable Long id) {
        MovimientoResponse cuenta = movimientoService.obtenerMovimientoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cuenta);
    }

    @Operation(summary = "Registrar movimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento creado"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<MovimientoResponse> createMovimiento(@RequestBody MovimientoDTO movimiento) {
        MovimientoResponse nuevoMovimiento = movimientoService.createMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMovimiento);
    }

    @Operation(summary = "Actualizar movimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento actualizado"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponse> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoRequest movimiento) {
        MovimientoResponse movimientoResp = movimientoService.actualizarMovimiento(id, movimiento);
        return ResponseEntity.status(HttpStatus.OK).body(movimientoResp);
    }

    @Operation(summary = "Eliminar movimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movimiento eliminado"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }

}
