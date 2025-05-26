package br.app.tads.medvoce.Service;

import br.app.tads.medvoce.Config.responseBuilder.ResponseBuilder;
import br.app.tads.medvoce.Model.Dtos.Doctor;
import br.app.tads.medvoce.Model.Dtos.Patient;
import br.app.tads.medvoce.Model.enums.Status;
import br.app.tads.medvoce.Model.records.Users;
import br.app.tads.medvoce.Repository.DoctorRepository;
import br.app.tads.medvoce.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ResponseBuilder responseBuilder;

    public ResponseEntity<?> getAllActives() {
        List<br.app.tads.medvoce.Model.Patient> patientList = patientRepository.findAllByStatus(Status.ACTIVE);
        List<br.app.tads.medvoce.Model.Doctor> doctorList = doctorRepository.findAllByStatus(Status.ACTIVE);

        Users users = new Users(getDoctorsDTO(doctorList), getpatientsDTO(patientList));

        return responseBuilder.build(users, HttpStatus.OK);
    }

    private List<Doctor> getDoctorsDTO(List<br.app.tads.medvoce.Model.Doctor> doctorList) {
        List<Doctor> dto = new ArrayList<>();

        doctorList.forEach(doctor -> {
            dto.add(new Doctor(doctor));
        });

        return dto;
    }

    private List<Patient> getpatientsDTO(List<br.app.tads.medvoce.Model.Patient> patientList) {
        List<Patient> dto = new ArrayList<>();

        patientList.forEach(patient -> {
            dto.add(new Patient(patient));
        });

        return dto;
    }
}
