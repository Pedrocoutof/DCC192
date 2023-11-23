package com.example.trabalho_pratico.controllers;

import com.example.trabalho_pratico.middlewares.isAuthenticated;
import com.example.trabalho_pratico.models.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class HomeController {

    @GetMapping("/menu")
    public String menu(HttpSession session){
        if(isAuthenticated.main(session)) {
            return "menu";
        }
        return "redirect:/login";
    }

    @GetMapping("/error404")
    public void handle404(HttpServletRequest request) throws NoHandlerFoundException {
        throw new NoHandlerFoundException("GET", request.getRequestURI(), null);
    }

    @GetMapping("/errorJava")
    public String handleJava(Model model) throws NoHandlerFoundException {
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("errorCause", e.getCause());
                return "error/template_error";
            }
        return "menu";
    }

}
