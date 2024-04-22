package com.devsu.AccountMovementService.application.dto;

import com.devsu.AccountMovementService.application.responses.CuentaResponse;
import com.devsu.AccountMovementService.domain.model.Cuenta;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CuentaMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static List<CuentaResponse> toCuentaDTO(List<Cuenta> cuentas){
        return cuentas.stream().map(CuentaMapper::toCuentaResp)
                .collect(Collectors.toList());
    }

    public static CuentaDTO toCuentaDTO(Cuenta cuenta){
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    public static CuentaResponse toCuentaResp(Cuenta cuenta){
        return modelMapper.map(cuenta, CuentaResponse.class);
    }

    public static Cuenta toCuenta(CuentaDTO cuentaDTO) {
        return modelMapper.map(cuentaDTO, Cuenta.class);
    }
}
