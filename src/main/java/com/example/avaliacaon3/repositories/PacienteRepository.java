package com.example.avaliacaon3.repositories;

import com.example.avaliacaon3.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    public Optional<Paciente> findByCpf(String cpf);
    public Optional<Paciente> findByNome(String nome);
}
