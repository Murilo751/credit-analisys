package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.AnaliseConverter;
import com.example.creditanalisys.model.dtos.AnaliseDTO;
import com.example.creditanalisys.model.dtos.UserDTO;
import com.example.creditanalisys.model.entities.AnaliseCred;
import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import com.example.creditanalisys.repositories.AnaliseCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class AnaliseService {
    private AnaliseCredRepository analiseCredRepository;
    private AnaliseConverter analiseConverter;

//    public AnaliseCred createAnalise(AnaliseDTO analiseDTO){
//        SolicitacaoCredito solicitacaoCredito = soliCredService.getSolById(analiseDTO.getSolicitacao_id());
//        if(solicitacaoCredito == null){
//            throw new RuntimeException("Solicitação de crédito não encontrada com o ID: " + analiseDTO.getSolicitacao_id());
//        }
//
//        AnaliseCred analiseCred = AnaliseConverter.convertAnaliseCredToDTO(analiseDTO, soliCredService);
//        analiseCred.setSolicitacao(solicitacaoCredito);
//
//        return analiseCredRepository.save(analiseCred);
//    }

}
