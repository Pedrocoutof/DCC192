package com.example.trabalho_pratico.controllers.auth;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import static com.example.trabalho_pratico.config.Constants.SESSION_TIMEOUT;

@Controller
@SessionAttributes("user")
public class login {

    private boolean auth(String email, String password){
        return (email.equals("pedro@gmail.com") && password.equals("123"))
                ||
                (email.equals("admin@admin.com") && password.equals("123")) ;
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginPost(
            @RequestParam(name = "email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model
    ){
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        if(auth(email, password)){
            session.setAttribute("user", email);
            session.setMaxInactiveInterval(SESSION_TIMEOUT);
            session.removeAttribute("error");
            return "redirect:/menu";
        } else {
            session.setAttribute("error", "Credenciais inválidas. Tente novamente.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerPost(){
        return null;
    }
}
