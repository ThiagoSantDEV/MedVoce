package br.app.tads.medvoce.Controller;

import br.app.tads.medvoce.Model.MedicalRecord;
import br.app.tads.medvoce.Model.Patient;
import br.app.tads.medvoce.Service.MedicalRecordService;
import br.app.tads.medvoce.Service.PatientService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    MedicalRecordController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/by-patient/{patientId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByPatient(@PathVariable Long patientId) {
        Optional<Patient> optionalPatient = patientService.getPatientById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByPatient(patient);
            return ResponseEntity.ok(medicalRecords);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        Optional<MedicalRecord> medicalRecordOpt = medicalRecordService.getMedicalRecordById(id);
        return medicalRecordOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        try {
            medicalRecordService.deleteMedicalRecord(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
