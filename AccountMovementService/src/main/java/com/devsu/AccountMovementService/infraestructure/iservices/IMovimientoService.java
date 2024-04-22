package com.devsu.AccountMovementService.infraestructure.iservices;

import com.devsu.AccountMovementService.application.dto.MovimientoDTO;
import com.devsu.AccountMovementService.application.dto.MovimientoRequest;
import com.devsu.AccountMovementService.application.responses.MovimientoResponse;

import java.util.List;

public interface IMovimientoService {

    MovimientoResponse obtenerMovimientoById(Long id);

    List<MovimientoResponse> getAllMovimientosByCuenta(String cuenta);

    MovimientoResponse createMovimiento(MovimientoDTO movimiento);

    MovimientoResponse actualizarMovimiento(Long id, MovimientoRequest movimiento);

    void deleteMovimiento(Long id);
}
