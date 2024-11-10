package com.example.avaliacaon3.services;

import com.example.avaliacaon3.dtos.*;
import com.example.avaliacaon3.exceptions.ConflitoException;
import com.example.avaliacaon3.exceptions.NaoEncontradoException;
import com.example.avaliacaon3.mappers.ReceitaMapper;
import com.example.avaliacaon3.models.Paciente;
import com.example.avaliacaon3.models.Receita;
import com.example.avaliacaon3.models.Remedio;
import com.example.avaliacaon3.repositories.PacienteRepository;
import com.example.avaliacaon3.repositories.ReceitaRepository;
import com.example.avaliacaon3.repositories.RemedioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final PacienteRepository pacienteRepository;
    private final RemedioRepository remedioRepository;
    private final ReceitaMapper receitaMapper;

    public ReceitaService(ReceitaRepository receitaRepository,
                          PacienteRepository pacienteRepository,
                          RemedioRepository remedioRepository,
                          ReceitaMapper receitaMapper) {
        this.receitaRepository = receitaRepository;
        this.pacienteRepository = pacienteRepository;
        this.remedioRepository = remedioRepository;
        this.receitaMapper = receitaMapper;
    }

    public List<ReceitaResponseDTO> getAllReceitas() {
        return receitaRepository.findAll().stream().map(receitaMapper::toDto).toList();
    }

    public ReceitaResponseDTO insertReceita(CreateReceitaDTO createReceitaDTO) {
        Paciente paciente = pacienteRepository.findById(createReceitaDTO.pacienteUUID())
                .orElseThrow(() -> new NaoEncontradoException("Paciente não encontrado"));

        List<Remedio> remedios = createReceitaDTO.remediosUUID().stream()
                .map(uuid -> remedioRepository.findById(uuid)
                        .orElseThrow(() -> new NaoEncontradoException("Remédio não encontrado: " + uuid)))
                .collect(Collectors.toList());

        Receita receita = new Receita(createReceitaDTO.posologia(), paciente, remedios);
        Receita receitaSalva = receitaRepository.save(receita);

        return receitaMapper.toDto(receitaSalva);
    }

    public ReceitaResponseDTO getReceitaByUUID(UUID uuid) {
        return receitaMapper.toDto(receitaRepository.findById(uuid).orElseThrow(
                () -> new NaoEncontradoException("Receita não encontrada!")
        ));
    }

    public ReceitaResponseDTO updateReceita(UUID id, CreateReceitaDTO createReceitaDTO) {
        Receita receitaExistente = receitaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Receita não encontrada"));

        Paciente paciente = pacienteRepository.findById(createReceitaDTO.pacienteUUID())
                .orElseThrow(() -> new NaoEncontradoException("Paciente não encontrado"));

        List<Remedio> remedios = createReceitaDTO.remediosUUID().stream()
                .map(uuid -> remedioRepository.findById(uuid)
                        .orElseThrow(() -> new NaoEncontradoException("Remédio não encontrado: " + uuid)))
                .collect(Collectors.toList());

        receitaExistente.setPosologia(createReceitaDTO.posologia());
        receitaExistente.setPaciente(paciente);
        receitaExistente.setRemedios(remedios);

        Receita receitaAtualizada = receitaRepository.save(receitaExistente);
        return receitaMapper.toDto(receitaAtualizada);
    }

    public ReceitaResponseDTO deleteReceita(UUID id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Receita não encontrada"));

        receitaRepository.delete(receita);

        return receitaMapper.toDto(receita);
    }
}
