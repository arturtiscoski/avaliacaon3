package com.example.avaliacaon3.mappers;

import com.example.avaliacaon3.dtos.CreatePacienteDTO;
import com.example.avaliacaon3.dtos.CreateRemedioDTO;
import com.example.avaliacaon3.dtos.RemedioResponseDTO;
import com.example.avaliacaon3.models.Paciente;
import com.example.avaliacaon3.models.Remedio;
import org.springframework.stereotype.Component;

@Component
public class RemedioMapper {
    public RemedioResponseDTO toDto(Remedio receita) {
        return new RemedioResponseDTO(
                receita.getId(),
                receita.getNome(),
                receita.getDosagem(),
                receita.getDescricao()
        );
    }

    public Remedio toModel(CreateRemedioDTO createRemedioDTO) {
        return new Remedio(
                createRemedioDTO.nome(),
                createRemedioDTO.dosagem(),
                createRemedioDTO.descricao()
        );
    }
}
