package br.app.tads.medvoce.Model.Dtos;

import br.app.tads.medvoce.Model.enums.Status;

import java.util.Date;

public class Patient {
    private Long id;
    private String email;
    private String name;
    private Date birth;
    private String cpf;
    private Status status;

    public Patient() {
    }

    public Patient(br.app.tads.medvoce.Model.Patient patient) {
        this.id = patient.getId();
        this.email = patient.getEmail();
        this.name = patient.getName();
        this.birth = patient.getBirth();
        this.cpf = patient.getCpf();
        this.status = patient.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
