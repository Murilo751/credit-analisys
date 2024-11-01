package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.SolCredDTO;
import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import com.example.creditanalisys.model.entities.Status;
import com.example.creditanalisys.model.entities.User;
import com.example.creditanalisys.repositories.SoliCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CredService {
    private SoliCredRepository soliCredRepository;
    private UserService userService;

    public SolCredDTO getSolCredById(Long id){
        return DozerConverter.parseObject(soliCredRepository.findById(id), SolCredDTO.class);
    }
    public SolCredDTO createSolCred(SolCredDTO solCredDTO, Long userId) {
        User catchUser = DozerConverter.parseObject(userService.getUserById(userId), User.class);
        if(catchUser == null){
            throw new RuntimeException("Solicitação de crédito não encontrada com o ID: " + userId);

        }
        SolicitacaoCredito solicitacaoCredito = DozerConverter.parseObject(solCredDTO, SolicitacaoCredito.class);
        solicitacaoCredito.setStatus(Status.PENDENTE);
        SolicitacaoCredito savedSolCred = soliCredRepository.save(solicitacaoCredito);
        return DozerConverter.parseObject(savedSolCred, SolCredDTO.class);
    }
    public ResponseEntity<SolCredDTO> deleteSolicitacaoCredito(Long id){
        Optional<SolicitacaoCredito> solicitacaoCreditoOptional = soliCredRepository.findById(id);
        if (solicitacaoCreditoOptional.isPresent()){
            SolicitacaoCredito solicitacao = solicitacaoCreditoOptional.get();
            soliCredRepository.delete(solicitacao);
            return ResponseEntity.ok(DozerConverter.parseObject(solicitacao, SolCredDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
    public List<SolCredDTO> getAllSolitacoes(){
        List<SolicitacaoCredito> solicitacoes = soliCredRepository.findAll();
        return DozerConverter.parseListObjects(solicitacoes, SolCredDTO.class);
    }
    public ResponseEntity<SolCredDTO> updateSolicitacaoCredito(Long id, SolCredDTO solCredDTO){
        SolicitacaoCredito solicitacaoCredito = DozerConverter.parseObject(getSolCredById(id), SolicitacaoCredito.class);
        if (solicitacaoCredito != null){
            solicitacaoCredito.setValor(solCredDTO.getValor());
            solicitacaoCredito.setHistoricoCredito(solCredDTO.getHistoricoCredito());
            soliCredRepository.save(solicitacaoCredito);
            return ResponseEntity.ok(DozerConverter.parseObject(solicitacaoCredito, SolCredDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
