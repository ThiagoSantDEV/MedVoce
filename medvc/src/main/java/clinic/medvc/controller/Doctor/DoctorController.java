package clinic.medvc.controller.Doctor;

import clinic.medvc.model.Doctor;
import clinic.medvc.model.User;
import clinic.medvc.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("v1/doctors")
public class DoctorController {

    public DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody CreateDoctorDto createDoctorDto) {

        var doctorId = doctorService.createDoctor(createDoctorDto);
        return ResponseEntity.created(URI.create("/v1/doctors/" + doctorId.toString())).build();

    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") String doctorId){
        return null;
    }



}
