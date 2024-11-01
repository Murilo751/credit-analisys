package com.example.creditanalisys.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimiteDTO {
    private Long id;
    private BigDecimal valor;
    private LocalDate data_Aprovacao;
    private Long user_id;
}
