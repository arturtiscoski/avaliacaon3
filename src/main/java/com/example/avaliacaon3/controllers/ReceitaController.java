package com.example.avaliacaon3.controllers;

import com.example.avaliacaon3.dtos.CreateReceitaDTO;
import com.example.avaliacaon3.dtos.ReceitaDTO;
import com.example.avaliacaon3.dtos.ReceitaResponseDTO;
import com.example.avaliacaon3.dtos.ReceitaResponseDTO;
import com.example.avaliacaon3.services.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReceitaResponseDTO> getAllReceitas() {
        return receitaService.getAllReceitas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReceitaResponseDTO getReceita(@PathVariable String id){
        return receitaService.getReceitaByUUID(UUID.fromString(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReceitaResponseDTO createReceita(@RequestBody @Valid CreateReceitaDTO receitaDto){
        return receitaService.insertReceita(receitaDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReceitaResponseDTO updateReceita(@PathVariable String id, @RequestBody CreateReceitaDTO receitaDTO){
        return receitaService.updateReceita(UUID.fromString(id), receitaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReceitaResponseDTO deleteReceita(@PathVariable String id){
        return receitaService.deleteReceita(UUID.fromString(id));
    }
}
