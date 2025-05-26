package br.app.tads.medvoce.Repository;

import br.app.tads.medvoce.Model.Doctor;
import br.app.tads.medvoce.Model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByStatus(Status status);
    Doctor findByEmailAndStatus(String email, Status status);

    Doctor findByEmail(String mail);

    boolean existsByEmail(String email);

    List<Doctor> findByNameContainingAndStatus(String name, Status status);

    List<Doctor> findByNameContaining(String doctorName);
}
