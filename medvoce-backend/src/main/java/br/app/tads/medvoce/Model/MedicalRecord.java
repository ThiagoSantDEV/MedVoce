package br.app.tads.medvoce.Model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "medical_record")
@Table(name = "medical_record")
@EqualsAndHashCode(of = "id")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id", unique = true)
    private Patient patient;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "medicalRecord-consultation")
    private List<Consultation> consultations = new ArrayList<>();

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "medicalRecord-exam")
    private List<Exam> exams = new ArrayList<>(); 

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "medicalRecord-revenue")
    private List<Prescription> prescriptions = new ArrayList<>();

    public MedicalRecord() {}

    public MedicalRecord(Patient patient) {
        this.patient = patient;
        this.consultations = new ArrayList<>();
        this.exams = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Consultation> getConsultations() {
        if (consultations == null) {
            consultations = new ArrayList<>();
        }
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
