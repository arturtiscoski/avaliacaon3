package com.example.avaliacaon3.controllers;

import com.example.avaliacaon3.dtos.*;
import com.example.avaliacaon3.services.RemedioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
    RemedioService remedioService;

    public RemedioController(RemedioService remedioService) {
        this.remedioService = remedioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RemedioResponseDTO> getAllRemedios() {
        return remedioService.getAllRemedios();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RemedioResponseDTO getRemedio(@PathVariable String id){
        return remedioService.getRemedioByUUID(UUID.fromString(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RemedioResponseDTO createRemedio(@RequestBody @Valid CreateRemedioDTO remedioDto){
        return remedioService.insertRemedio(remedioDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RemedioResponseDTO updateRemedio(@PathVariable String id, @RequestBody RemedioDTO remedioDTO){
        return remedioService.updateRemedio(UUID.fromString(id), remedioDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RemedioResponseDTO deleteRemedio(@PathVariable String id){
        return remedioService.deleteRemedio(UUID.fromString(id));
    }

}
