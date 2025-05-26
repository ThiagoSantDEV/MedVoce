package br.app.tads.medvoce.Controller;

import br.app.tads.medvoce.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/get-all-actives")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllActives(){
        return usersService.getAllActives();
    }
}
