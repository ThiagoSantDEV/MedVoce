package br.app.tads.medvoce.Controller;

import br.app.tads.medvoce.Model.Patient;
import br.app.tads.medvoce.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegisterController {

    @Autowired
    private UsersService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody Patient user) {
        try {
            // Validate input
            if (user.getEmail() == null || user.getPassword() == null || user.getName() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required.");
            }

            // Register user
            userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration.");
        }
    }
}