package com.example.creditanalisys.controllers;

import com.example.creditanalisys.model.dtos.SolCredDTO;
import com.example.creditanalisys.model.entities.SolicitacaoCredito;
import com.example.creditanalisys.services.CredService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/solicitacao")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SolCredController {

    private CredService credService;

    @PostMapping(value = "/create")
    public SolCredDTO create(@RequestBody @Valid SolCredDTO solCredDTO) {
        SolicitacaoCredito solicitacaoCredito = new SolicitacaoCredito();
    }
}
