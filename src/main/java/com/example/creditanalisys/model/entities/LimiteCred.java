package com.example.creditanalisys.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "limite")
@Table(name = "limite")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimiteCred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private BigDecimal valor;
    private LocalDate data_Aprovacao;

    public LimiteCred(BigDecimal valor){
        this.valor = valor;
    }

    private BigDecimal limitePadrao = new BigDecimal("100000");

    public BigDecimal calcularLimitePorHistorico(String historicoCred) {
        switch (historicoCred.toLowerCase()) {
            case "excelente":
                return limitePadrao.multiply(new BigDecimal("1.5")); //aumenta o limite em 50%
            case "bom":
                return limitePadrao;
            case "moderado":
                return limitePadrao.multiply(new BigDecimal("0.8")); //reduz o limite em 20%
            case "ruim":
                return limitePadrao.multiply(new BigDecimal("0.5")); //reduz o limite em 50%
            default:
                return BigDecimal.ZERO; //crédito negado para histórico desconhecido
        }
    }
}
