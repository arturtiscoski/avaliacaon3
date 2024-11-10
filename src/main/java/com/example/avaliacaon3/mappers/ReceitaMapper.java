package com.example.avaliacaon3.mappers;

import com.example.avaliacaon3.dtos.CreatePacienteDTO;
import com.example.avaliacaon3.dtos.CreateReceitaDTO;
import com.example.avaliacaon3.dtos.CreateRemedioDTO;
import com.example.avaliacaon3.dtos.ReceitaResponseDTO;
import com.example.avaliacaon3.models.Paciente;
import com.example.avaliacaon3.models.Receita;
import com.example.avaliacaon3.models.Remedio;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ReceitaMapper {
    public ReceitaResponseDTO toDto(Receita receita) {
        return new ReceitaResponseDTO(
                receita.getId(),
                receita.getPosologia(),
                receita.getDataPrescricao(),
                receita.getPaciente(),
                receita.getRemedios()
        );
    }

    public Receita toModel(CreateReceitaDTO createReceitaDTO) {
        Paciente paciente = new Paciente(createReceitaDTO.pacienteUUID());
        List<Remedio> remedioList = new ArrayList<>();

        for (UUID remedioUUID : createReceitaDTO.remediosUUID()) {
            remedioList.add(new Remedio(remedioUUID));
        }

        return new Receita(
                createReceitaDTO.posologia(),
                paciente,
                remedioList
        );
    }
}
