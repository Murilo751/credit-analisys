package com.example.creditanalisys.controllers;

import com.example.creditanalisys.converter.DozerConverter;
import com.example.creditanalisys.model.dtos.AnaliseDTO;
import com.example.creditanalisys.model.entities.AnaliseCred;
import com.example.creditanalisys.services.AnaliseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/analise")

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AnaliseController {
    private AnaliseService analiseService;

    @PostMapping(value = "/create")
    public ResponseEntity<AnaliseDTO> createAnalise(@RequestBody @Valid AnaliseDTO analiseDTO) {
        AnaliseCred analiseCreate = analiseService.createAnalise(analiseDTO);
        return ResponseEntity.ok(DozerConverter.parseObject(analiseCreate, AnaliseDTO.class));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnaliseDTO> findAnaliseById(@PathVariable @Valid Long id){
        return ResponseEntity.ok(analiseService.getAnaliseById(id));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<AnaliseDTO>> getAllAnalises(){
        return ResponseEntity.ok(analiseService.getAllAnalises());
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<AnaliseDTO> updateAnalise(@RequestBody @Valid AnaliseDTO analiseDTO, @PathVariable @Valid Long id){
        return analiseService.updateAnalise(analiseDTO,id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<AnaliseDTO> deleteAnalise(@PathVariable @Valid Long id){
        return analiseService.deleteAnalise(id);
    }

    @PostMapping("/calculo/{solicitacaoId}")
    public ResponseEntity<AnaliseDTO> calcularCredito(@PathVariable Long solicitacaoId) {
        AnaliseDTO analiseDTO = analiseService.calcCred(solicitacaoId);
        return ResponseEntity.ok(analiseDTO);
    }

}
