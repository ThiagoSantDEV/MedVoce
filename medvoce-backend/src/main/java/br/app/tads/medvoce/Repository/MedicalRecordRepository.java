package br.app.tads.medvoce.Repository;

import br.app.tads.medvoce.Model.MedicalRecord;
import br.app.tads.medvoce.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    
    List<MedicalRecord> findByPatient(Patient patient);

    Optional<MedicalRecord> findFirstByPatient(Patient patient);

}
