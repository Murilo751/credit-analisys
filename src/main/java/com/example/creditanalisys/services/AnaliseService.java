package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.AnaliseDTO;
import com.example.creditanalisys.model.dtos.SolCredDTO;
import com.example.creditanalisys.model.entities.AnaliseCred;
import com.example.creditanalisys.model.entities.LimiteCred;
import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import com.example.creditanalisys.model.entities.Status;
import com.example.creditanalisys.repositories.AnaliseCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AnaliseService {
    private AnaliseCredRepository analiseCredRepository;
    private CredService credService;
    private LimiteCred limiteCred;

    public AnaliseCred createAnalise(AnaliseDTO analiseDTO,Long idSolicitacao){

        SolicitacaoCredito solicitacaoCredito = DozerConverter.parseObject(credService.getSolCredById(analiseDTO.getSolicitacaoId()), SolicitacaoCredito.class);
        if(solicitacaoCredito == null){
            throw new RuntimeException("Solicitação de crédito não encontrada com o ID: " + analiseDTO.getSolicitacaoId());

        }

        AnaliseCred analiseCred = new AnaliseCred();
        analiseCred.setResultado(Status.PENDENTE);
        analiseCred.setSolicitacaoId(solicitacaoCredito.getId());


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

    public boolean isCredAprovado(SolicitacaoCredito solicitacaoCredito){
        BigDecimal limiteAprov = limiteCred.calcularLimitePorHistorico(solicitacaoCredito.getHistoricoCredito());

        return solicitacaoCredito.getValor().compareTo(limiteAprov) < 0;
    }

    public AnaliseDTO calcCred(Long solicitacaoId){
        SolicitacaoCredito solCred = DozerConverter.parseObject(credService.getSolCredById(solicitacaoId),SolicitacaoCredito.class);
        if (solCred == null){
            throw new RuntimeException("Solicitação de crédito não encontrada com o ID: " + solicitacaoId);
        }

        AnaliseCred analiseCred = new AnaliseCred();
        analiseCred.setSolicitacaoId(solCred.getId());

        boolean aprovado = isCredAprovado(solCred);
        analiseCred.setResultado(aprovado ? Status.APROVADO : Status.REJEITADO);
        analiseCred.setDescricao(aprovado ? "Crédito aprovado" : "Crédito rejeitado");
        analiseCred.setDataAnalise(LocalDate.now());

        AnaliseCred savedAnalise = analiseCredRepository.save(analiseCred);

        if (aprovado){
            solCred.setStatus(Status.APROVADO);

        }else {
            solCred.setStatus(Status.REJEITADO);
        }

        SolCredDTO solCredDTO = DozerConverter.parseObject(solCred, SolCredDTO.class);
        credService.updateSolicitacaoCredito(solCred.getId(), solCredDTO);


        return DozerConverter.parseObject(savedAnalise, AnaliseDTO.class);
    }

}
