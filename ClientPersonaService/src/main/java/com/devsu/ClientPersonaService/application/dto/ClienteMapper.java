package com.devsu.ClientPersonaService.application.dto;

import com.devsu.ClientPersonaService.domain.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class ClienteMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ClienteDTO toClienteDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public static List<ClienteDTO> toClienteDTOList(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteMapper::toClienteDTO)
                .collect(Collectors.toList());
    }

    public static Cliente toCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setContrasena(dto.getContrasena());
        cliente.setEstado(dto.getEstado());

        return cliente;
    }
}
