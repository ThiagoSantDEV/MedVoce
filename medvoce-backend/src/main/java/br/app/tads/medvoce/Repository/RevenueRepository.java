package br.app.tads.medvoce.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.app.tads.medvoce.Model.Prescription;
import br.app.tads.medvoce.Model.Doctor;
import br.app.tads.medvoce.Model.Patient;

public interface RevenueRepository extends JpaRepository<Prescription, Long> {
    
    List<Prescription> findByPatient(Patient patient);

    List<Prescription> findByDoctor(Doctor doctor);

    List<Prescription> findByDateTime(LocalDateTime dateTime);

}
