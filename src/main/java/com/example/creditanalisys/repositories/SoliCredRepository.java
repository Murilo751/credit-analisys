package com.example.creditanalisys.repositories;

import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoliCredRepository extends JpaRepository<SolicitacaoCredito, Long> {

}
