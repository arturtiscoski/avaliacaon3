package com.example.avaliacaon3.repositories;

import com.example.avaliacaon3.models.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RemedioRepository extends JpaRepository<Remedio, UUID> {
    public Optional<Remedio> findByNome(String nome);
}
