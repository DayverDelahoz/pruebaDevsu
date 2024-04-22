package com.devsu.AccountMovementService.infraestructure.iservices;

import com.devsu.AccountMovementService.application.dto.CuentaDTO;
import com.devsu.AccountMovementService.application.responses.CuentaResponse;

import java.util.List;

public interface ICuentaService {

    List<CuentaResponse> obtenerCuentas();

    CuentaResponse obtenerCuentasByID(Long id);

    CuentaResponse crearCuenta(CuentaDTO clienteDTO);

    CuentaResponse actualizarCuenta(Long id, CuentaDTO clienteDTO);

    void eliminarCuenta(Long id);
}
