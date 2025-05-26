package br.app.tads.medvoce.Model.records;

import br.app.tads.medvoce.Model.Dtos.Doctor;
import br.app.tads.medvoce.Model.Dtos.Patient;

import java.util.List;

public record Users (List<Doctor> doctorsList, List<Patient> patientsList) {
}
