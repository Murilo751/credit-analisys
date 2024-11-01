package com.example.creditanalisys.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoDTO {
    private Long id;
    private int pontuacao;
    private int pagamentosPontuais;
    private int incidentes;
    private BigDecimal dividas;
    private Long userId;
}
