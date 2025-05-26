package br.app.tads.medvoce.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import br.app.tads.medvoce.Config.responseBuilder.ResponseBuilder;
import br.app.tads.medvoce.Model.MedicalRecord;
import br.app.tads.medvoce.Model.Prescription;
import br.app.tads.medvoce.Service.RevenueService;

@RestController
@RequestMapping("/revenue")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrescriptionController {

    @Autowired
    private RevenueService revenueService;

    private ResponseBuilder responseBuilder;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllRevenues() {
        return revenueService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<?> getRevenueById(@PathVariable Long id) {
        return revenueService.getById(id);
    }

    @GetMapping("/by-patient/{patientId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<?> getByPatient(@PathVariable Long patientId) {
        if (patientId == null || patientId <= 0) {
            return responseBuilder.build("ID de paciente inválido.", HttpStatus.BAD_REQUEST);
        }
        return revenueService.getByPatientId(patientId);
    }

    @GetMapping("/by-doctor/{doctorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<?> getByDoctor(@PathVariable Long doctorId) {
        if (doctorId == null || doctorId <= 0) {
            return responseBuilder.build("ID de Médico inválido.", HttpStatus.BAD_REQUEST);
        }
        return revenueService.getByDoctorId(doctorId);
    }

    @GetMapping("/by-date")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<?> getRevenueByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime dateTime) {
        return revenueService.getRevenueByDate(dateTime);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> updateRevenue(@PathVariable Long id, @RequestBody Prescription updatedPrescription) {
        return revenueService.updateRevenue(id, updatedPrescription);
    }

    @PostMapping("/add/{medicalRecordId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicalRecord> addRevenueToMedicalRecord(
            @PathVariable Long medicalRecordId, @RequestBody Prescription prescription) {
        try {
            MedicalRecord updatedMedicalRecord = revenueService.addRevenue(medicalRecordId, prescription);
            return ResponseEntity.ok(updatedMedicalRecord);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
