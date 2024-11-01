package com.example.creditanalisys.controllers;

import com.example.creditanalisys.model.dtos.HistoricoDTO;
import com.example.creditanalisys.services.HistoricoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<HistoricoDTO> findHiistoricoById(@PathVariable @Valid Long id){
        return historicoService.getHistoricoById(id);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<HistoricoDTO>> getAllHistorico(){
        return historicoService.getAllHistorico();
    }

    @PutMapping(value = "/update/{id}")
    public  ResponseEntity<HistoricoDTO> updateHistorico(@RequestBody @Valid HistoricoDTO historicoDTO, @PathVariable @Valid Long id){
        return historicoService.updateHistorico(historicoDTO,id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HistoricoDTO> deleteHistorico(@PathVariable @Valid Long id){
        return historicoService.deleteHistorico(id);
    }
}
