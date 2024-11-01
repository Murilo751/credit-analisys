package com.example.creditanalisys.repositories;

import com.example.creditanalisys.model.entities.HistoricoCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoCredRepository extends JpaRepository<HistoricoCred, Long> {
}
