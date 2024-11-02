package com.example.creditanalisys.controllers;

import com.example.creditanalisys.model.dtos.LimiteDTO;
import com.example.creditanalisys.services.LimiteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/limite")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LimiteController {
    private LimiteService limiteService;

    @PostMapping(value = "/create/{userId}")
    public ResponseEntity<LimiteDTO> createLimit(@RequestBody @Valid LimiteDTO limiteDTO, @PathVariable @Valid Long userId){
        LimiteDTO limiteCred = limiteService.createLimit(limiteDTO, userId);
        return ResponseEntity.ok(limiteCred);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<LimiteDTO> findLimitById(@PathVariable @Valid Long id){
        return ResponseEntity.ok(limiteService.getLimitById(id));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<LimiteDTO>> getAllHistorico(){
        return ResponseEntity.ok(limiteService.getAllLimite());
    }

    @PutMapping(value = "/update/{id}")
    public  ResponseEntity<LimiteDTO> updateHistorico(@RequestBody @Valid LimiteDTO limiteDTO, @PathVariable @Valid Long id){
        return limiteService.updateLimite(limiteDTO,id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<LimiteDTO> deleteHistorico(@PathVariable @Valid Long id){
        return limiteService.deleteLimite(id);
    }
}
