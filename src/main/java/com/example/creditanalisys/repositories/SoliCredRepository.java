package com.example.creditanalisys.repositories;

import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoliCredRepository extends JpaRepository<SolicitacaoCredito, Long> {

}
