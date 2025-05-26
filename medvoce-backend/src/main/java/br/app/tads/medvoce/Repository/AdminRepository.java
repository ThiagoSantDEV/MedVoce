package br.app.tads.medvoce.Repository;

import br.app.tads.medvoce.Model.Admin;
import br.app.tads.medvoce.Model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findAllByStatus(Status status);
    Admin findByEmailAndStatus(String email, Status status);

    Admin findByEmail(String mail);

    boolean existsByEmail(String email);

    List<Admin> findByNameContainingAndStatus(String name, Status status);
}
