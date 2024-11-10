package com.example.avaliacaon3.mappers;

import com.example.avaliacaon3.dtos.CreatePacienteDTO;
import com.example.avaliacaon3.dtos.PacienteResponseDTO;
import com.example.avaliacaon3.models.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    public PacienteResponseDTO toDto(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getIdade()
        );
    }

    public Paciente toModel(CreatePacienteDTO pacienteCreateDTO) {
        return new Paciente(
                pacienteCreateDTO.nome(),
                pacienteCreateDTO.cpf(),
                pacienteCreateDTO.idade()
        );
    }
}
