package com.example.creditanalisys.repositories;

import com.example.creditanalisys.model.entities.AnaliseCred;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnaliseCredRepository extends JpaRepository<AnaliseCred, Long> {
    List<AnaliseCred> findByResultado(String resultado);

}
