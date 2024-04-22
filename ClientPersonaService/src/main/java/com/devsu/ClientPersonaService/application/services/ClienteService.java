package com.devsu.ClientPersonaService.application.services;

import com.devsu.ClientPersonaService.application.dto.ClienteDTO;
import com.devsu.ClientPersonaService.application.dto.ClienteMapper;
import com.devsu.ClientPersonaService.domain.model.Cliente;
import com.devsu.ClientPersonaService.domain.repository.ClienteRepository;
import com.devsu.ClientPersonaService.infraestucture.iservices.IClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO consultaCliente(Long id) {
        var clientOpt = clienteRepository.findById(id);
        if(clientOpt.isPresent()){
            return ClienteMapper.toClienteDTO(clientOpt.get());
        }
        throw new EntityNotFoundException("Cliente con ID " + id + " no encontrado");
    }

    @Override
    public List<ClienteDTO> obtenerClientes() {
        var clients = clienteRepository.findAll();
        return ClienteMapper.toClienteDTOList(clients);
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO client) {
        var cliente = ClienteMapper.toCliente(client);
        var newCliente = clienteRepository.save(cliente);
        return ClienteMapper.toClienteDTO(newCliente);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        var clienteOpt = clienteRepository.findById(id);
        if(clienteOpt.isPresent()){
            var cliente = clienteOpt.get();
            cliente.setNombre(cliente.getNombre());
            cliente.setGenero(cliente.getGenero());
            cliente.setEdad(cliente.getEdad());
            cliente.setIdentificacion(cliente.getIdentificacion());
            cliente.setDireccion(cliente.getDireccion());
            cliente.setTelefono(cliente.getTelefono());
            cliente.setContrasena(clienteDTO.getContrasena());
            cliente.setEstado(clienteDTO.getEstado());
            return ClienteMapper.toClienteDTO(clienteRepository.save(cliente));
        }else {
            throw new EntityNotFoundException("Cliente con ID " + id + " no encontrado");
        }

    }

    @Override
    public void eliminarCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cliente con ID " + id + " no encontrado");
        }
    }
}
