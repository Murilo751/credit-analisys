package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.AnaliseDTO;
import com.example.creditanalisys.model.dtos.SolCredDTO;
import com.example.creditanalisys.model.entities.AnaliseCred;
import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import com.example.creditanalisys.repositories.AnaliseCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AnaliseService {
    private AnaliseCredRepository analiseCredRepository;
    private CredService credService;

    public AnaliseCred createAnalise(AnaliseDTO analiseDTO){
        SolCredDTO solicitacaoCreditoDTO = credService.getSolCredById(analiseDTO.getSolicitacao_id());
        if(solicitacaoCreditoDTO == null){
            throw new RuntimeException("Solicitação de crédito não encontrada com o ID: " + analiseDTO.getSolicitacao_id());
        }

        AnaliseCred analiseCred = null;
        analiseCred.setSolicitacao(DozerConverter.parseObject(solicitacaoCreditoDTO, SolicitacaoCredito.class));


        return analiseCredRepository.save(analiseCred);
    }

}
