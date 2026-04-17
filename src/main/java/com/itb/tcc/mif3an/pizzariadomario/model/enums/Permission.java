package com.itb.tcc.mif3an.pizzariadomario.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {

    ADMIN_READ("ADMIN_READ"),
    ADMIN_UPDATE("ADMIN_UPDATE"),
    ADMIN_CREATE("ADMIN_CREATE"),
    ADMIN_DELETE("ADMIN_DELETE"),
    FUNCIONARIO_READ("FUNCIONARIO_READ"),
    FUNCIONARIO_UPDATE("FUNCIONARIO_UPDATE"),
    FUNCIONARIO_CREATE("FUNCIONARIO_CREATE"),
    FUNCIONARIO_DELETE("FUNCIONARIO_DELETE"),
    CLIENTE_READ("CLIENTE_READ"),
    CLIENTE_UPDATE("CLIENTE_UPDATE"),
    CLIENTE_CREATE("CLIENTE_CREATE"),
    CLIENTE_DELETE("CLIENTE_DELETE");

    private final String permission;

}
