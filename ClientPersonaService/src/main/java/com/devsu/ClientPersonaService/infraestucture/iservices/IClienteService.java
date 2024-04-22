package com.devsu.ClientPersonaService.infraestucture.iservices;

import com.devsu.ClientPersonaService.application.dto.ClienteDTO;

import java.util.List;

public interface IClienteService {
    ClienteDTO consultaCliente(Long id);

    List<ClienteDTO> obtenerClientes();

    ClienteDTO crearCliente(ClienteDTO client);

    ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO);

    void eliminarCliente(Long id);
}
