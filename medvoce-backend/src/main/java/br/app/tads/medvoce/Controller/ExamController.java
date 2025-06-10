package br.app.tads.medvoce.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExamController {

    @GetMapping("/exam-list")
    public String examList() {
        return "exam-list";
    }
}