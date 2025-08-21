package se.systementor.unittester.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.systementor.unittester.services.CalculatorService;

@Controller
public class AppController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("tal1", 18);
        model.addAttribute("tal2", "K");
        model.addAttribute("action", "plus");
        return "home";
    }

    @PostMapping("/")
    String homePost(Model model, String tal1, String tal2, String action){
        model.addAttribute("tal1", tal1);
        model.addAttribute("tal2", tal2);
        model.addAttribute("action", action);

        int result = calculatorService.Calculate(Integer.parseInt(tal1),Integer.parseInt(tal2),action);
        model.addAttribute("Result", result);

        return "home";
    }
}
