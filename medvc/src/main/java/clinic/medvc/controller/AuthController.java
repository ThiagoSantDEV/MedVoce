package clinic.medvc.controller;

import clinic.medvc.model.User;
import clinic.medvc.repository.UserRepository;
import clinic.medvc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request, HttpServletRequest httpRequest) {
        try {
            User user = userRepository.findByEmail(request.email())
                    .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

            if (!passwordEncoder.matches(request.password(), user.getPassword())) {
                return ResponseEntity.status(401).body("Credenciais inválidas");
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login bem-sucedido");
            response.put("userId", user.getIdUser());
            response.put("email", user.getEmail());
            response.put("name", user.getName());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @GetMapping("/check")
    public String check() {
        System.out.println("✅ Endpoint /v1/auth/check acessado!");
        return "API Auth está funcionando";
    }


}

