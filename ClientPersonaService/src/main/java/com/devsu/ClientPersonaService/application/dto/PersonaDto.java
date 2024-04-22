package com.devsu.ClientPersonaService.application.dto;

import com.devsu.ClientPersonaService.domain.model.Cliente;

import lombok.Getter;
import lombok.Setter;

//@Builder
@Setter
@Getter
public class PersonaDto {
    private Cliente cliente;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
