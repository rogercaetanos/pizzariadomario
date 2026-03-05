package com.itb.tcc.mif3an.pizzariadomario.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FUNCIONARIO")
public class Funcionario extends Usuario {

    @Column(nullable = true, length = 20)
    private String cnh;
}
