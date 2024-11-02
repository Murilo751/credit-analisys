package com.example.creditanalisys.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "limite")
@Table(name = "limite")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LimiteCred {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private BigDecimal valor;
    private LocalDate dataAprovacao;

    @Getter
    private BigDecimal limitePadrao = new BigDecimal("10000");

    public BigDecimal calcularLimitePorHistorico(String historicoCred) {
        if (historicoCred != null) {
            historicoCred = historicoCred.toLowerCase();
            switch (historicoCred.toLowerCase()) {
                case "excelente":
                    return limitePadrao.multiply(new BigDecimal("1.5"));
                case "bom":
                    return limitePadrao;
                case "moderado":
                    return limitePadrao.multiply(new BigDecimal("0.8"));
                case "ruim":
                    return limitePadrao.multiply(new BigDecimal("0.5"));
                default:
                    return limitePadrao.multiply(new BigDecimal("0.5"));
            }
        }else{
            throw new IllegalArgumentException("O histórico de crédito é nulo.");
        }
    }
}
