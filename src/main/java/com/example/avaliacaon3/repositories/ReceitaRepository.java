package com.example.avaliacaon3.repositories;

import com.example.avaliacaon3.models.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface ReceitaRepository extends JpaRepository<Receita, UUID> {
    public Optional<Receita> findByPosologia(String posologia);
}
