package com.example.creditanalisys.services;

import com.example.creditanalisys.repositories.LimiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LimiteService {
    private LimiteRepository limiteRepository;
    private LimiteService limiteService;

}
