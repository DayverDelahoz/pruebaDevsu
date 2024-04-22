package com.devsu.AccountMovementService.application.services;

import com.devsu.AccountMovementService.application.dto.CuentaDTO;
import com.devsu.AccountMovementService.application.dto.CuentaMapper;
import com.devsu.AccountMovementService.application.responses.CuentaResponse;
import com.devsu.AccountMovementService.domain.repository.ClienteRepository;
import com.devsu.AccountMovementService.domain.repository.CuentaRepository;
import com.devsu.AccountMovementService.infraestructure.iservices.ICuentaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService implements ICuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public CuentaResponse crearCuenta(CuentaDTO cuentaDTO) {
        var cltOpt = clienteRepository.findById(cuentaDTO.getIdCliente());
        var ctaNew = CuentaMapper.toCuenta(cuentaDTO);

        if(cltOpt.isPresent()){
            ctaNew.setCliente(cltOpt.get());
            return CuentaMapper.toCuentaResp(cuentaRepository.save(ctaNew));
        }else{
            throw new EntityNotFoundException("No existe el cliente con ID: "+cuentaDTO.getIdCliente());
        }

    }

    @Override
    public List<CuentaResponse> obtenerCuentas() {
        return CuentaMapper.toCuentaDTO(cuentaRepository.findAll());
    }

    @Override
    public CuentaResponse obtenerCuentasByID(Long id) {
        var cuentaOpt = cuentaRepository.findById(id);
        if (cuentaOpt.isPresent()){
            return CuentaMapper.toCuentaResp(cuentaOpt.get());
        } else {
          throw new EntityNotFoundException("No se encuentra la cuenta con ID: "+id);
        }
    }

    @Override
    public CuentaResponse actualizarCuenta(Long id, CuentaDTO cuentaDTO) {
        var cuentaOpt = cuentaRepository.findById(id);
        if (cuentaOpt.isPresent()){
            var cuentaMod = cuentaOpt.get();
            cuentaMod.setSaldo(cuentaDTO.getSaldo());
            cuentaMod.setEstado(cuentaDTO.getEstado());
            cuentaMod.setNumero(cuentaDTO.getNumero());
            cuentaMod.setTipo(cuentaDTO.getTipo());
            if(!cuentaMod.getCliente().getId().equals(cuentaDTO.getIdCliente())){
                var cltOpt = clienteRepository.findById(cuentaDTO.getIdCliente());
                if(cltOpt.isPresent()){
                    cuentaMod.setCliente(cltOpt.get());
                }else{
                    throw new EntityNotFoundException("No existe el cliente con ID: "+cuentaDTO.getIdCliente());
                }
            }
            return CuentaMapper.toCuentaResp(cuentaRepository.save(cuentaOpt.get()));
        } else {
            throw new EntityNotFoundException("No se encuentra la cuenta con ID: "+id);
        }
    }

    @Override
    public void eliminarCuenta(Long id) {
        var cuentaOpt = cuentaRepository.findById(id);
        if (cuentaOpt.isPresent()){
            cuentaRepository.delete(cuentaOpt.get());
        } else {
            throw new EntityNotFoundException("No se encuentra la cuenta con ID: "+id);
        }
    }
}
