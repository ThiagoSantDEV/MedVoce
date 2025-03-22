package clinic.medvc.controller.Doctor;

import java.time.LocalDate;

public record CreateDoctorDto (String name, String cpf, String email, String password, LocalDate dateOfBirth){
}
