package com.devsu.ClientPersonaService.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente extends Persona{

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private String estado;

}
