package com.example.creditanalisys.controllers;

import com.example.creditanalisys.model.dtos.SolCredDTO;
import com.example.creditanalisys.model.dtos.UserDTO;
import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import com.example.creditanalisys.services.CredService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/solicitacao")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SolCredController {

    private CredService credService;

    @PostMapping(value = "/create")
    public ResponseEntity<SolCredDTO> create(@RequestBody @Valid SolCredDTO solCredDTO) {
        SolCredDTO solicitacaoCredito = credService.createSolCred(solCredDTO);
        return ResponseEntity.ok(solicitacaoCredito);
    }

    @GetMapping(value = "/getSolCredById/{id}")
    ResponseEntity<SolCredDTO> getSolCredById(@PathVariable @Valid Long id){
        return ResponseEntity.ok(credService.getSolCredById(id));
    }

    @PutMapping(value = "/updateSolicitacaoCredito/{id}")
    public ResponseEntity<SolCredDTO> updateSolicitacaoCredito(@RequestBody @Valid SolCredDTO solCredDTO, @PathVariable @Valid Long id){
        return credService.updateSolicitacaoCredito(id, solCredDTO);
    }

    @GetMapping(value = "/getAllSolicitacoes")
    public ResponseEntity<List<SolCredDTO>> getAllSolicitacoes(){
        return ResponseEntity.ok(credService.getAllSolitacoes());
    }

    @DeleteMapping(value = "/deleteSolicitacao/{id}")
    public ResponseEntity<SolCredDTO> deleteSolicitacao(Long id){
        return credService.deleteSolicitacaoCredito(id);
    }

}
