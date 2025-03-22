package clinic.medvc.service;

import clinic.medvc.controller.Doctor.CreateDoctorDto;
import clinic.medvc.model.Doctor;
import clinic.medvc.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
public class DoctorService {
    private  DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public UUID createDoctor(CreateDoctorDto createDoctorDto) {
        var entity = new Doctor(
          null,
                createDoctorDto.name(),
                createDoctorDto.cpf(),
                createDoctorDto.email(),
                createDoctorDto.password(),
                createDoctorDto.dateOfBirth(),
                Instant.now(),
                null
        );

        var doctorSaved = doctorRepository.save(entity);
        return  doctorSaved.getIdDoctor();
    }
}
