package com.devsu.AccountMovementService.application.dto;

import com.devsu.AccountMovementService.application.responses.MovimientoResponse;
import com.devsu.AccountMovementService.domain.model.Movimiento;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovimientoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static List<MovimientoResponse> toMovimientoResponse(List<Movimiento> movs){
        return movs.stream().map(MovimientoMapper::toMovimientoResp)
                .collect(Collectors.toList());
    }

    public static MovimientoDTO toMovimientoDTO(Movimiento mov){
        return modelMapper.map(mov, MovimientoDTO.class);
    }

    public static MovimientoResponse toMovimientoResp(Movimiento mov){
        var movResp = modelMapper.map(mov, MovimientoResponse.class);
        movResp.setCuenta(mov.getCuenta().getNumero());
        return movResp;
    }

    public static Movimiento toMovimiento(MovimientoDTO movDTO) {
        return modelMapper.map(movDTO, Movimiento.class);
    }
}
