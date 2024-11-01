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
}
