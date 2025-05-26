package br.app.tads.medvoce.Repository;

import br.app.tads.medvoce.Model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    List<Consultation> findByPatientId(Long patientId);

    List<Consultation> findByDoctorId(Long doctorId);

    List<Consultation> findByDateTime(LocalDateTime dateTime);

    List<Consultation> findByDateTimeBetween(Date start, Date end);

    List<Consultation> findBySpecialtyContainingIgnoreCase(String specialty);
}
