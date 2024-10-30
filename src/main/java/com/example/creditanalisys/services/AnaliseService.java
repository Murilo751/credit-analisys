package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.AnaliseDTO;
import com.example.creditanalisys.model.dtos.SolCredDTO;
import com.example.creditanalisys.model.entities.AnaliseCred;
import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import com.example.creditanalisys.repositories.AnaliseCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

        AnaliseCred analiseCred = new AnaliseCred();
        analiseCred.setSolicitacao(DozerConverter.parseObject(solicitacaoCreditoDTO, SolicitacaoCredito.class));


        return analiseCredRepository.save(analiseCred);
    }

    public AnaliseDTO getAnaliseById(Long id){
        return DozerConverter.parseObject(analiseCredRepository.findById(id), AnaliseDTO.class);
    }

    public ResponseEntity<AnaliseDTO> updateAnalise(AnaliseDTO analiseDTO, Long id){
        AnaliseCred analiseCredSearch = DozerConverter.parseObject(getAnaliseById(id), AnaliseCred.class);
        if (analiseCredSearch == null){
            return ResponseEntity.notFound().build();
        } else {
            analiseCredSearch.setDescricao(analiseDTO.getDescricao());
            analiseCredSearch.setResultado(analiseDTO.getResultado());
            analiseCredSearch.setDataAnalise(analiseDTO.getDataAnalise());
            AnaliseCred saveAnalise = analiseCredRepository.save(analiseCredSearch);
            return ResponseEntity.ok(DozerConverter.parseObject(saveAnalise, AnaliseDTO.class));
        }
    }

    public List<AnaliseDTO> getAllAnalises(){
        List<AnaliseCred> analiseCreds = analiseCredRepository.findAll();
        return DozerConverter.parseListObjects(analiseCreds, AnaliseDTO.class);
    }

    public ResponseEntity<AnaliseDTO> deleteAnalise(Long id){
        AnaliseCred analiseCred = DozerConverter.parseObject(getAnaliseById(id), AnaliseCred.class);
        if (analiseCred != null){
            analiseCredRepository.delete(analiseCred);
            return ResponseEntity.ok(DozerConverter.parseObject(analiseCred, AnaliseDTO.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
