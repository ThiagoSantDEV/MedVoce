package br.app.tads.medvoce.Repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.app.tads.medvoce.Model.Exam;
import br.app.tads.medvoce.Model.Patient;

public interface ExaminationMedical extends JpaRepository<Exam, Long> {

    List<Exam> findByPatient(Patient patient);

    List<Exam> findByPatientAndDateTime(Patient patient, LocalDateTime dateTime);

    List<Exam> findByDateTime(LocalDateTime dateTime);

    List<Exam> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

}