package com.example.creditanalisys.converter;

import com.example.creditanalisys.model.dtos.AnaliseDTO;
import com.example.creditanalisys.model.entities.AnaliseCred;
import org.springframework.stereotype.Component;

@Component
public class AnaliseConverter {
    public static AnaliseDTO convertAnaliseCredToDTO(AnaliseCred analiseCred){
        AnaliseDTO analiseDTO = new AnaliseDTO();
        analiseDTO.setId(analiseCred.getId());
        analiseDTO.setDescricao(analiseCred.getDescricao());
        analiseDTO.setResultado(analiseCred.getResultado());
        analiseDTO.setDataAnalise(analiseCred.getDataAnalise());
        analiseDTO.setSolicitacao_id(analiseCred.getSolicitacao().getId());
        return analiseDTO;
    }

    public AnaliseCred convertDTOToAnaliseCred(AnaliseDTO analiseDTO){
        AnaliseCred analiseCred = new AnaliseCred();
        analiseCred.setId(analiseDTO.getId());
        analiseCred.setDescricao(analiseDTO.getDescricao());
        analiseCred.setResultado(analiseDTO.getResultado());
        analiseCred.setDataAnalise(analiseDTO.getDataAnalise());
        return analiseCred;
    }
}
