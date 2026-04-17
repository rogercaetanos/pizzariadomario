package com.itb.tcc.mif3an.pizzariadomario.model.entity;

import com.itb.tcc.mif3an.pizzariadomario.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Informa que o padrão utilizado é uma única tabela para todos os usuários
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING) // Informa qual é o nome da coluna que vai ser utilizada
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor   // Construtor padrão
@AllArgsConstructor  // Construtor com todos os atributos
@Builder             // Forma diferenciadada para criar objetos
public class Usuario implements UserDetails {

    @Id                         // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Incremento (identificado sequencialmente de 1 em 1)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = true, length = 100)
    private String nome;
    @Column(nullable = true, length = 20)
    private String cpf;
    @Column(nullable = false, length = 45)
    private String email;
    @Column(nullable = true)
    private LocalDate dataNascimento;
    @Column(nullable = false, length = 250)
    private String password;
    @Column(nullable = true, length = 2)
    private String sexo;
    @Column(nullable = true, length = 100)
    private String logradouro;
    @Column(nullable = true, length = 9)
    private String cep;
    @Column(nullable = true, length = 45)
    private String bairro;
    @Column(nullable = true, length = 45)
    private String cidade;
    @Column(nullable = true, length = 2)
    private String uf;
    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private TipoUsuario tipoUsuario;

    private boolean codStatus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return tipoUsuario.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
