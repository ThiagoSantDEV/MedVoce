package br.app.tads.medvoce.Model.Dtos;

import br.app.tads.medvoce.Model.enums.Status;

import java.util.Date;

public class Doctor {
    private Long id;
    private String email;
    private String name;
    private Date birth;
    private String crm;
    private Status status;

    public Doctor() {
    }

    public Doctor(br.app.tads.medvoce.Model.Doctor doctor) {
        this.id = doctor.getId();
        this.email = doctor.getEmail();
        this.name = doctor.getName();
        this.birth = doctor.getBirth();
        this.crm = doctor.getCrm();
        this.status = doctor.getStatus();
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
