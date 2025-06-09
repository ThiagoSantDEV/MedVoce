package br.app.tads.medvoce.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
