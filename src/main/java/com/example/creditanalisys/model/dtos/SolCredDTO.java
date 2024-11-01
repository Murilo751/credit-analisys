package com.example.creditanalisys.model.dtos;

import com.example.creditanalisys.model.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SolCredDTO {
    private Long id;
    private Long user_id;
    private Status status;
    private BigDecimal valor;
    private LocalDate data_solicitacao;
    private String historicoCredito;
}
