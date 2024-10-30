package com.example.creditanalisys.model.dtos;

import com.example.creditanalisys.model.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseDTO {
    private Long id;
    private String descricao;
    private Status resultado;
    private LocalDate dataAnalise;
    private Long solicitacaoId;
}
