package com.example.creditanalisys.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "solicitacao")
@Table(name = "solicitacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private String Status;
    private BigDecimal valor;
    private LocalDate data_solicitacao;
}
