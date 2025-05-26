package br.app.tads.medvoce.Service;

import br.app.tads.medvoce.Config.responseBuilder.ResponseBuilder;
import br.app.tads.medvoce.Model.Admin;
import br.app.tads.medvoce.Model.enums.Status;
import br.app.tads.medvoce.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {


    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ResponseBuilder responseBuilder;

    public ResponseEntity<?> getAllActive (){
        return responseBuilder.build(adminRepository.findAllByStatus(Status.ACTIVE), HttpStatus.OK);
    }

    public ResponseEntity<?> getAll() {
        return responseBuilder.build(adminRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> add(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail()))
            return responseBuilder.build("Email já cadastrado", HttpStatus.UNAUTHORIZED);

        admin.setStatus(Status.ACTIVE);
        Admin saved = adminRepository.save(admin);
        return responseBuilder.build(saved, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getByName(Admin admin) {

        List<Admin> admins = adminRepository.findByNameContainingAndStatus(admin.getName(), Status.ACTIVE);

        return new ResponseEntity<>(admins, HttpStatus.OK);

    }

    public ResponseEntity<?> getByEmail(Admin admin) {

        Admin adminSaved = adminRepository.findByEmailAndStatus(admin.getEmail(), Status.ACTIVE);

        return new ResponseEntity<>(adminSaved, HttpStatus.OK);

    }

    public ResponseEntity<?> edit(Admin admin) {

        Admin obj = adminRepository.findByEmail(admin.getEmail());
        if (obj == null)
            return responseBuilder.build("Usuário não localizado!", HttpStatus.NOT_FOUND);

        obj.setName(admin.getName());
        obj.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        obj.setStatus(admin.getStatus());
        Admin saved = adminRepository.save(obj);

        return responseBuilder.build(saved, HttpStatus.OK);

    }

    public ResponseEntity<?> delete(Admin admin) {

        Admin adminSaved = adminRepository.findByEmailAndStatus(admin.getEmail(), Status.ACTIVE);
        if (adminSaved == null)
            return responseBuilder.build("Usuário não localizado!", HttpStatus.NOT_FOUND);

        adminSaved.setStatus(Status.INACTIVE);
        adminRepository.save(adminSaved);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
