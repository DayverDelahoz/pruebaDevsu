package com.devsu.AccountMovementService.infraestructure.client.impl;

import com.devsu.AccountMovementService.application.responses.reporte.ClienteDTO;
import com.devsu.AccountMovementService.infraestructure.client.ClienteRestAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ClienteRestAdapterImpl implements ClienteRestAdapter {

    private final WebClient webClient;

    public ClienteRestAdapterImpl(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:8082/devsu/clientes").build();;
    }

    @Override
    public Mono<ClienteDTO> obtenerCliente(Long id) {
        return webClient.get().uri("/{id}", id)
                .retrieve()
                .bodyToMono(ClienteDTO.class);
    }
}
