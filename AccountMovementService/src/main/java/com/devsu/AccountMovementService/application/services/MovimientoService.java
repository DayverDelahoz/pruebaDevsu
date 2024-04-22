package com.devsu.AccountMovementService.application.services;

import com.devsu.AccountMovementService.application.dto.MovimientoDTO;
import com.devsu.AccountMovementService.application.dto.MovimientoMapper;
import com.devsu.AccountMovementService.application.dto.MovimientoRequest;
import com.devsu.AccountMovementService.application.responses.MovimientoResponse;
import com.devsu.AccountMovementService.application.exceptions.SaldoNoDisponibleException;
import com.devsu.AccountMovementService.domain.repository.CuentaRepository;
import com.devsu.AccountMovementService.domain.repository.MovimientoRepository;
import com.devsu.AccountMovementService.infraestructure.iservices.IMovimientoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MovimientoService implements IMovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public void deleteMovimiento(Long id) {
        var movOpt = movimientoRepository.findById(id);

        if(movOpt.isPresent()){
            var mov = movOpt.get();
            movimientoRepository.delete(mov);
        }
    }

    @Override
    public MovimientoResponse actualizarMovimiento(Long id, MovimientoRequest request) {
        var movOpt = movimientoRepository.findById(id);

        if(movOpt.isPresent()){
            var mov = movOpt.get();
            mov.setTipo(request.getTipo());
            mov.setFecha(request.getFecha());
            mov.setValor(request.getValor());
            mov.setSaldo(request.getSaldo());
            return MovimientoMapper.toMovimientoResp(movimientoRepository.save(mov));
        }else{
            throw new EntityNotFoundException("No existe registro del movimiento con ID: "+id);
        }
    }

    @Override
    public MovimientoResponse createMovimiento(MovimientoDTO movDTO) {
        var cuentaRel = cuentaRepository.findByNumero(movDTO.getNroCuenta());

        if (cuentaRel.isPresent()){
           var cuenta = cuentaRel.get();
           if(movDTO.getValor().compareTo(cuenta.getSaldo()) < 0){
               if(!cuenta.getEstado().equals("ACTIVA")){
                   throw new SaldoNoDisponibleException("Cuenta inactiva");
               }
               var mov = MovimientoMapper.toMovimiento(movDTO);
               mov.setCuenta(cuenta);
               BigDecimal nuevoSaldo = cuenta.getSaldo().subtract(movDTO.getValor());
               mov.setSaldo(nuevoSaldo);
               movimientoRepository.save(mov);

               cuenta.setSaldo(nuevoSaldo);
               cuentaRepository.save(cuenta);
               return MovimientoMapper.toMovimientoResp(mov);
           } else {
               throw new SaldoNoDisponibleException("Saldo no disponible");
           }
        }else{
            throw new EntityNotFoundException("Cuenta no encontrada");
        }
    }

    @Override
    public MovimientoResponse obtenerMovimientoById(Long id) {
        var movOpt = movimientoRepository.findById(id);

        if(movOpt.isPresent()) {
            var mov = movOpt.get();
            return MovimientoMapper.toMovimientoResp(mov);
        } else {
          throw new EntityNotFoundException("No existe registro del movimiento con ID: "+id);
        }
    }

    @Override
    public List<MovimientoResponse> getAllMovimientosByCuenta(String nro) {

        var cuentaAsoc = cuentaRepository.findByNumero(nro);
        if(cuentaAsoc.isPresent()){
            return MovimientoMapper.toMovimientoResponse(cuentaAsoc.get().getMovimientos());
        } else {
            throw new EntityNotFoundException("No existe el número de cuenta");
        }

    }
}
