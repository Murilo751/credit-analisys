package com.example.creditanalisys.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "historico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoricoCred {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pontuacao;
    private int pagamentosPontuais;
    private int incidentes;
    private BigDecimal dividas;

    private Long userId;
}
