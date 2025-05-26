package br.app.tads.medvoce.Repository;

import br.app.tads.medvoce.Model.Agenda;
import br.app.tads.medvoce.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByDoctor(Doctor doctor);

    List<Agenda> findByDoctorAndAvailableTrue(Doctor doctor);

    List<Agenda> findByStartDateTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Agenda> findByDoctorAndStartDateTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);

}
