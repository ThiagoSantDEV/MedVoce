package br.app.tads.medvoce.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.app.tads.medvoce.Config.responseBuilder.ResponseBuilder;
import br.app.tads.medvoce.Model.Doctor;
import br.app.tads.medvoce.Model.MedicalRecord;
import br.app.tads.medvoce.Model.Patient;
import br.app.tads.medvoce.Model.Prescription;
import br.app.tads.medvoce.Repository.DoctorRepository;
import br.app.tads.medvoce.Repository.MedicalRecordRepository;
import br.app.tads.medvoce.Repository.PatientRepository;
import br.app.tads.medvoce.Repository.RevenueRepository;

@Service
public class RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public ResponseEntity<?> getAll() {
        List<Prescription> prescriptions = revenueRepository.findAll();

        if (prescriptions.isEmpty()) {
            return responseBuilder.build("Nenhuma receita cadastrada.", HttpStatus.NO_CONTENT);
        }

        return responseBuilder.build(prescriptions, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Long id) {
        if (id <= 0) {
            return responseBuilder.build("ID inválido.", HttpStatus.BAD_REQUEST);
        }

        Optional<Prescription> revenueOpt = revenueRepository.findById(id);

        return revenueOpt
                .<ResponseEntity<?>>map(prescription -> responseBuilder.build(prescription, HttpStatus.OK))
                .orElseGet(() -> responseBuilder.build("Receita não encontrada.", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> getByPatientId(Long patientId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (patientOpt.isEmpty()) {
            return responseBuilder.build("Paciente não encontrado.", HttpStatus.NOT_FOUND);
        }

        List<Prescription> prescriptions = revenueRepository.findByPatient(patientOpt.get());
        if (prescriptions.isEmpty()) {
            return responseBuilder.build("Nenhuma receita encontrada para o paciente.", HttpStatus.NOT_FOUND);
        }

        return responseBuilder.build(prescriptions, HttpStatus.OK);
    }

    public ResponseEntity<?> getByDoctorId(Long doctorId) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isEmpty()) {
            return responseBuilder.build("Médico não encontrado.", HttpStatus.NOT_FOUND);
        }

        List<Prescription> prescriptions = revenueRepository.findByDoctor(doctorOpt.get());
        if (prescriptions.isEmpty()) {
            return responseBuilder.build("Nenhuma receita encontrada para o médico.", HttpStatus.NOT_FOUND);
        }

        return responseBuilder.build(prescriptions, HttpStatus.OK);
    }

    public ResponseEntity<?> getRevenueByDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return ResponseEntity.badRequest().body("Data inválida ou não informada.");
        }
        List<Prescription> prescriptions = revenueRepository.findByDateTime(dateTime);

        if (prescriptions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma Receita encontrada para a data informada.");
        }

        return ResponseEntity.ok(prescriptions);
    }

    public ResponseEntity<?> updateRevenue(Long id, Prescription updatedPrescription) {
        
        Optional<Prescription> optionalRevenue = revenueRepository.findById(id);
        if (!optionalRevenue.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receita não encontrada.");
        }

        Prescription existingPrescription = optionalRevenue.get();
       
        existingPrescription.setMedications(updatedPrescription.getMedications());
        existingPrescription.setDosage(updatedPrescription.getDosage());
        existingPrescription.setRecommendations(updatedPrescription.getRecommendations());
        existingPrescription.setDateTime(updatedPrescription.getDateTime());

        revenueRepository.save(existingPrescription);

        return ResponseEntity.ok(existingPrescription);
    }

    public MedicalRecord addRevenue(Long medicalRecordId, Prescription prescription) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findById(medicalRecordId);
        if (optionalMedicalRecord.isPresent()) {
            MedicalRecord medicalRecord = optionalMedicalRecord.get();
            prescription.setMedicalRecord(medicalRecord);
            medicalRecord.getPrescriptions().add(prescription);
            return medicalRecordRepository.save(medicalRecord);
        } else {
            throw new IllegalArgumentException("Prontuário não encontrado");
        }
    }
}
