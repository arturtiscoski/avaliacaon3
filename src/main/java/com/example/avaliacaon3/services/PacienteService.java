package com.example.avaliacaon3.services;

import com.example.avaliacaon3.dtos.CreatePacienteDTO;
import com.example.avaliacaon3.dtos.PacienteDTO;
import com.example.avaliacaon3.dtos.PacienteRequestDTO;
import com.example.avaliacaon3.dtos.PacienteResponseDTO;
import com.example.avaliacaon3.exceptions.ConflitoException;
import com.example.avaliacaon3.exceptions.NaoEncontradoException;
import com.example.avaliacaon3.mappers.PacienteMapper;
import com.example.avaliacaon3.models.Paciente;
import com.example.avaliacaon3.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;
    private PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    public List<PacienteResponseDTO> getAllPacientes() {
        return pacienteRepository.findAll().stream().map(pacienteMapper::toDto).toList();
    }

    public PacienteResponseDTO getPacienteByUUID(UUID uuid) {
        return pacienteMapper.toDto(pacienteRepository.findById(uuid).orElseThrow(
                () -> new NaoEncontradoException("Paciente não encontrato!")
        ));
    }

    public PacienteResponseDTO insertPaciente(CreatePacienteDTO pacienteCreateDTO) {
        pacienteRepository.findByCpf(pacienteCreateDTO.cpf()).ifPresent(
                paciente -> {
                    throw new ConflitoException("Paciente cpm esse CPF já cadastrado");
                }
        );

        return pacienteMapper.toDto(pacienteRepository.save(pacienteMapper.toModel(pacienteCreateDTO)));
    }

    public PacienteResponseDTO updatePaciente(UUID id, PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Paciente não encontrado")
        );
        if (pacienteDTO.nome() != null) paciente.setNome(pacienteDTO.nome());
        if (pacienteDTO.idade() != null) paciente.setIdade(paciente.getIdade());
        pacienteRepository.findByNome(paciente.getNome()).ifPresent(
                pacienteCadastrado -> {
                    if (!pacienteCadastrado.getId().equals(paciente.getId())) {
                        throw new ConflitoException("Paciente já cadastrada");
                    }
                }
        );
        return pacienteMapper.toDto(pacienteRepository.save(paciente));
    }

    public PacienteResponseDTO deletePaciente(UUID id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Paciente não encontrado")
        );
        pacienteRepository.delete(paciente);
        return pacienteMapper.toDto(paciente);
    }

}
