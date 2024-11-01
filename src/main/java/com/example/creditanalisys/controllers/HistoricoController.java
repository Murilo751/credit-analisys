package com.example.creditanalisys.controllers;

import com.example.creditanalisys.model.dtos.HistoricoDTO;
import com.example.creditanalisys.services.HistoricoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
@RequestMapping(value = "/api/historico")
public class HistoricoController {

    private HistoricoService historicoService;
    @PostMapping(value = "/create")
    public ResponseEntity<HistoricoDTO> createHisorico(@RequestBody @Valid HistoricoDTO historicoDTO){
        HistoricoDTO historicoCred = historicoService.createHistorico(historicoDTO);
        return ResponseEntity.ok(historicoCred);
    }
}
