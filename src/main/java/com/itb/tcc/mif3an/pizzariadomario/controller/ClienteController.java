package com.itb.tcc.mif3an.pizzariadomario.controller;


import com.itb.tcc.mif3an.pizzariadomario.auth.AuthenticationResponse;
import com.itb.tcc.mif3an.pizzariadomario.auth.AuthenticationService;
import com.itb.tcc.mif3an.pizzariadomario.auth.RegisterRequest;
import com.itb.tcc.mif3an.pizzariadomario.model.entity.Cliente;
import com.itb.tcc.mif3an.pizzariadomario.model.enums.TipoUsuario;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    private final AuthenticationService authenticationService;

    public ClienteController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //@PermitAll
   // @PreAuthorize("hasAuthority('USER_CREATE')")
    @PostMapping
    public ResponseEntity<AuthenticationResponse> registerCliente (@RequestBody RegisterRequest registerRequest) {
        registerRequest.setTipoUsuario(TipoUsuario.CLIENTE);
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
}
