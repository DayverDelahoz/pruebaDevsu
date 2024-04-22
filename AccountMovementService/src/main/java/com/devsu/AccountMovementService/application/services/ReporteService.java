package com.devsu.AccountMovementService.application.services;

import com.devsu.AccountMovementService.application.responses.reporte.ReporteEstadoCuenta;
import com.devsu.AccountMovementService.domain.repository.CuentaRepository;
import com.devsu.AccountMovementService.infraestructure.client.ClienteRestAdapter;
import com.devsu.AccountMovementService.infraestructure.iservices.IReporteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReporteService implements IReporteService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRestAdapter clienteRestAdapter;

    public ReporteService(CuentaRepository cuentaRepository, ClienteRestAdapter clienteRestAdapter) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRestAdapter = clienteRestAdapter;
    }

    @Override
    public ReporteEstadoCuenta generarReporteEstadoCuenta(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {

        var client = clienteRestAdapter.obtenerCliente(idCliente).block();

        if(client!=null){
            var cuentas = cuentaRepository.findAll();
            //No alcancé a completar
        }

        return ReporteEstadoCuenta.builder()
                .estado("OK").build();
    }
}
