package br.app.tads.medvoce.Repository;

import br.app.tads.medvoce.Model.Patient;
import br.app.tads.medvoce.Model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByStatus(Status status);
    Patient findByEmailAndStatus(String email, Status status);

    Patient findByEmail(String mail);

    boolean existsByEmail(String email);

    List<Patient> findByNameAndStatus(String name, Status status);

    List<Patient> findByNameContainingAndStatus(String name, Status status);
}
