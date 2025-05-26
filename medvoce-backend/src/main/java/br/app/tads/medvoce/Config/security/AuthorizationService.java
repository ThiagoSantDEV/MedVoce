package br.app.tads.medvoce.Config.security;

import br.app.tads.medvoce.Model.enums.Status;
import br.app.tads.medvoce.Repository.AdminRepository;
import br.app.tads.medvoce.Repository.DoctorRepository;
import br.app.tads.medvoce.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = patientRepository.findByEmailAndStatus(username, Status.ACTIVE);
        if (user == null)
            user = doctorRepository.findByEmailAndStatus(username, Status.ACTIVE);
        if (user == null)
            user = adminRepository.findByEmailAndStatus(username, Status.ACTIVE);

        return user;
    }
}
