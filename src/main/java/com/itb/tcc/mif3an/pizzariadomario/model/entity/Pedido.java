package com.itb.tcc.mif3an.pizzariadomario.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "Pedido")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false, length = 10)
    private String numeroPedido;
    @Column(nullable = false)
    private double valorPedido;
    @Column(nullable = false)
    private LocalDateTime dataHoraPedido;
    @Column(nullable = true)
    private LocalDateTime dataHoraEntrega;
    @Column(nullable = false, length = 20)
    private String status;
    private boolean codStatus;

    // Relacionamento entre as classes

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

}
