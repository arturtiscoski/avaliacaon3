package com.example.avaliacaon3.controllers;

import com.example.avaliacaon3.dtos.CreatePacienteDTO;
import com.example.avaliacaon3.dtos.PacienteDTO;
import com.example.avaliacaon3.dtos.PacienteResponseDTO;
import com.example.avaliacaon3.services.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteResponseDTO> getAllPacientes() {
        return pacienteService.getAllPacientes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteResponseDTO getPaciente(@PathVariable String id){
        return pacienteService.getPacienteByUUID(UUID.fromString(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponseDTO createPaciente(@RequestBody @Valid CreatePacienteDTO pacienteDto){
        return pacienteService.insertPaciente(pacienteDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteResponseDTO updatePaciente(@PathVariable String id, @RequestBody PacienteDTO pacienteDTO){
        return pacienteService.updatePaciente(UUID.fromString(id), pacienteDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteResponseDTO deletePaciente(@PathVariable String id){
        return pacienteService.deletePaciente(UUID.fromString(id));
    }

}
