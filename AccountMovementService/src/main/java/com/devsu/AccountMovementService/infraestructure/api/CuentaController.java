package com.devsu.AccountMovementService.infraestructure.api;

import com.devsu.AccountMovementService.application.dto.CuentaDTO;
import com.devsu.AccountMovementService.application.responses.CuentaResponse;
import com.devsu.AccountMovementService.infraestructure.iservices.ICuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final ICuentaService cuentaService;

    public CuentaController(ICuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @Operation(summary = "Consulta de todos las cuentas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Servicio no disponible")
    })
    @GetMapping
    public ResponseEntity<List<CuentaResponse>> findALlCuentas(){
        var cuentas = cuentaService.obtenerCuentas();
        return ResponseEntity.status(HttpStatus.OK).body(cuentas);
    }

    @Operation(summary = "Consulta de cuenta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Servicio no disponible")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CuentaResponse> findCuenta(@RequestParam Long id){
        var cuenta = cuentaService.obtenerCuentasByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(cuenta);
    }

    @Operation(summary = "Crea una nueva cuenta asociada a un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(@RequestBody CuentaDTO clienteDTO) {
        CuentaResponse ctaCreada = cuentaService.crearCuenta(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ctaCreada);
    }

    @Operation(summary = "Actualiza una cuenta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponse> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaDTO clienteDTO) {
        CuentaResponse cuentaAct = cuentaService.actualizarCuenta(id, clienteDTO);
        return ResponseEntity.status(HttpStatus.OK).body(cuentaAct);
    }

    @Operation(summary = "Elimina una cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cuenta eliminada"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }

}