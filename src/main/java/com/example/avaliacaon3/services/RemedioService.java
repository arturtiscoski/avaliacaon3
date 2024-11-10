package com.example.avaliacaon3.services;

import com.example.avaliacaon3.dtos.*;
import com.example.avaliacaon3.exceptions.ConflitoException;
import com.example.avaliacaon3.exceptions.NaoEncontradoException;
import com.example.avaliacaon3.mappers.PacienteMapper;
import com.example.avaliacaon3.mappers.RemedioMapper;
import com.example.avaliacaon3.models.Paciente;
import com.example.avaliacaon3.models.Remedio;
import com.example.avaliacaon3.repositories.PacienteRepository;
import com.example.avaliacaon3.repositories.RemedioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RemedioService {
    private RemedioRepository remedioRepository;
    private RemedioMapper remedioMapper;

    public RemedioService(RemedioRepository remedioRepository, RemedioMapper remedioMapper) {
        this.remedioRepository = remedioRepository;
        this.remedioMapper = remedioMapper;
    }

    public List<RemedioResponseDTO> getAllRemedios() {
        return remedioRepository.findAll().stream().map(remedioMapper::toDto).toList();
    }

    public RemedioResponseDTO getRemedioByUUID(UUID uuid) {
        return remedioMapper.toDto(remedioRepository.findById(uuid).orElseThrow(
                () -> new NaoEncontradoException("Remédio não encontrado!")
        ));
    }

    public RemedioResponseDTO insertRemedio(CreateRemedioDTO createRemedioDTO) {
        remedioRepository.findByNome(createRemedioDTO.nome()).ifPresent(
                paciente -> {
                    throw new ConflitoException("Remédio com esse nome já cadastrado!");
                }
        );

        return remedioMapper.toDto(remedioRepository.save(remedioMapper.toModel(createRemedioDTO)));
    }

    public RemedioResponseDTO updateRemedio(UUID id, RemedioDTO remedioDTO) {
        Remedio remedio = remedioRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Remédio não encontrado")
        );
        if (remedioDTO.nome() != null) remedio.setNome(remedioDTO.nome());
        if (remedioDTO.dosagem() != null) remedio.setDosagem(remedioDTO.dosagem());
        remedioRepository.findByNome(remedio.getNome()).ifPresent(
                remedioCadastrado -> {
                    if (!remedioCadastrado.getId().equals(remedio.getId())) {
                        throw new ConflitoException("Remédio já cadastrado");
                    }
                }
        );
        return remedioMapper.toDto(remedioRepository.save(remedio));
    }

    public RemedioResponseDTO deleteRemedio(UUID id) {
        Remedio remedio = remedioRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Remédio não encontrado")
        );
        remedioRepository.delete(remedio);
        return remedioMapper.toDto(remedio);
    }
}
