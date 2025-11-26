package br.com.alurafood.pagamentos.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 19)
    private String numero;

    @Column(nullable = false, length = 7)
    private String expiracao;

    @Transient
    private String codigoDeSeguranca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Column(name = "pedido_id", nullable = false)
    private Long pedidoId;

    @Column(name = "forma_de_pagamento_id", nullable = false)
    private Long formaDePagamentoId;
}
