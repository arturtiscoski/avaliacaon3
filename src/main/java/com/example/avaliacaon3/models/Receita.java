package com.example.avaliacaon3.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String posologia; //numero de vezes e a quantidade a ser tomada para cada remédio, será um texto de todos os remédios
    @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    private Date dataPrescricao; //data em que a receita foi criada, irá ser gerado automaticamente

    @ManyToOne
    private Paciente paciente; //paciente associado a uma receita

    @JsonManagedReference
    @ManyToMany
    private List<Remedio> remedios; //lista de remédios

    public Receita () {

    }

    public Receita(String posologia, Paciente paciente, List<Remedio> remedios) {
        this.dataPrescricao = new Date();
        this.posologia = posologia;
        this.paciente = paciente;
        this.remedios = remedios;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public Date getDataPrescricao() {
        return dataPrescricao;
    }

    public void setDataPrescricao(Date dataPrescricao) {
        this.dataPrescricao = dataPrescricao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Remedio> getRemedios() {
        return remedios;
    }

    public void setRemedios(List<Remedio> remedios) {
        this.remedios = remedios;
    }
}
