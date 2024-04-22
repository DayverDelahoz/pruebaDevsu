package com.devsu.AccountMovementService.infraestructure.client;

import com.devsu.AccountMovementService.application.responses.reporte.ClienteDTO;
import reactor.core.publisher.Mono;

public interface ClienteRestAdapter {

    Mono<ClienteDTO> obtenerCliente(Long id);
}
