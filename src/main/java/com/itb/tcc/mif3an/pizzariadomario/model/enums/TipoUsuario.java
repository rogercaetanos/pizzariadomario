package com.itb.tcc.mif3an.pizzariadomario.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.itb.tcc.mif3an.pizzariadomario.model.enums.Permission.*;


@Getter
@AllArgsConstructor
public enum TipoUsuario {

    ADMIN(
            Set.of(
                  ADMIN_READ,
                  ADMIN_CREATE,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  FUNCIONARIO_READ,
                  FUNCIONARIO_CREATE,
                  FUNCIONARIO_UPDATE,
                  FUNCIONARIO_DELETE
            )
    ),
    CLIENTE(
            Set.of(
                    CLIENTE_READ,
                    CLIENTE_CREATE,
                    CLIENTE_UPDATE,
                    CLIENTE_DELETE
            )
    ),
    FUNCIONARIO(
            Set.of(
                    FUNCIONARIO_READ,
                    FUNCIONARIO_CREATE,
                    FUNCIONARIO_UPDATE,
                    FUNCIONARIO_DELETE
            )
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                         .stream()
                         .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                          .collect(Collectors.toList());
         authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
         return authorities;

    }

}
