package br.app.tads.medvoce.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.app.tads.medvoce.Model.Exam;
import br.app.tads.medvoce.Model.MedicalRecord;
import br.app.tads.medvoce.Service.ExaminationMedicaService;

@RestController
@RequestMapping("/exam")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExaminationMedicalController {

    @Autowired
    private ExaminationMedicaService examinationMedicaService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllExams() {
        return examinationMedicaService.getAllExams();
    }

    @GetMapping("/by-patient/{patientId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<?> getExamsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(examinationMedicaService.getExamsByPatient(patientId));
    }

    @GetMapping("/by-date")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PATIENT') or hasRole('DOCTOR')")
    public ResponseEntity<?> getExamsByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime dateTime) {
        return examinationMedicaService.getExamsByDate(dateTime);
    }

    @PutMapping("/edit/{examId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Exam> updateExam(@PathVariable Long examId, @RequestBody Exam updatedExam) {
        try {
            Exam exam = examinationMedicaService.updateExam(examId, updatedExam);
            return ResponseEntity.ok(exam);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add/{medicalRecordId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicalRecord> addExamToMedicalRecord(
            @PathVariable Long medicalRecordId, @RequestBody Exam exam) {
        try {
            MedicalRecord updatedMedicalRecord = examinationMedicaService.addExam(medicalRecordId, exam);
            return ResponseEntity.ok(updatedMedicalRecord);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
