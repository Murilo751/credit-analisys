package com.example.creditanalisys.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "analise")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnaliseCred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Status resultado;
    private LocalDate dataAnalise;

    @ManyToOne
    @JoinColumn(name = "solicitacaoId", nullable = false)
    private SolicitacaoCredito solicitacao;
}
