package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.SolCredDTO;
import com.example.creditanalisys.repositories.SoliCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CredService {
    private SoliCredRepository soliCredRepository;

    public SolCredDTO getSolCredById(Long id){
        return DozerConverter.parseObject(soliCredRepository.findById(id), SolCredDTO.class);
    }
}
