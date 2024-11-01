package com.example.creditanalisys.services;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.HistoricoDTO;
import com.example.creditanalisys.model.entities.HistoricoCred;
import com.example.creditanalisys.repositories.HistoricoCredRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
public class HistoricoService {
    private HistoricoCredRepository historicoCredRepository;

    public HistoricoDTO createHistorico(HistoricoDTO historicoDTO){
        HistoricoCred historicoCred = DozerConverter.parseObject(historicoDTO, HistoricoCred.class);
        HistoricoCred savedCred = historicoCredRepository.save(historicoCred);
        return DozerConverter.parseObject(savedCred, HistoricoDTO.class);
    }

    public ResponseEntity<HistoricoDTO> getHistoricoById(Long id){
        HistoricoCred historicoCred = historicoCredRepository.findById(id).orElse(null);
        return ResponseEntity.ok(DozerConverter.parseObject(historicoCred, HistoricoDTO.class));
    }

    public ResponseEntity<List<HistoricoDTO>> getAllHistorico(){
        List<HistoricoCred> historicoCreds = historicoCredRepository.findAll();
        return ResponseEntity.ok(DozerConverter.parseListObjects(historicoCreds, HistoricoDTO.class));
    }

    public ResponseEntity<HistoricoDTO> updateHistorico(HistoricoDTO historicoDTO, Long id){
        HistoricoCred historicoCred = historicoCredRepository.findById(id).orElse(null);
        if(historicoCred != null){
            historicoCred.setPontuacao(historicoDTO.getPontuacao());
            historicoCred.setPagamentosPontuais(historicoDTO.getPagamentosPontuais());
            historicoCred.setIncidentes(historicoDTO.getIncidentes());
            historicoCred.setDividas(historicoDTO.getDividas());
            HistoricoCred historicoSaved = historicoCredRepository.save(historicoCred);
            return ResponseEntity.ok(DozerConverter.parseObject(historicoSaved, HistoricoDTO.class));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public ResponseEntity<HistoricoDTO> deleteHistorico(Long id){
        HistoricoCred historicoCred = historicoCredRepository.findById(id).orElse(null);
        if(historicoCred != null){
            historicoCredRepository.delete(historicoCred);
            return ResponseEntity.ok(DozerConverter.parseObject(historicoCred, HistoricoDTO.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}