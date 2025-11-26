package br.com.ms.pagamentos.api.dto;

import br.com.ms.pagamentos.domain.model.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoDTO {

    private Long id;
    @NotNull
    @Positive
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Size(max = 19)
    @Column(nullable = false, length = 19)
    private String numero;

    @NotBlank
    @Size(max = 7)
    @Column(nullable = false, length = 7)
    private String expiracao;

    @NotBlank
    @Size(min = 3, max = 3)
    @Transient
    private String codigoDeSeguranca;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @NotNull
    @Column(name = "pedido_id", nullable = false)
    private Long pedidoId;

    @NotNull
    @Column(name = "forma_de_pagamento_id", nullable = false)
    private Long formaDePagamentoId;
}
