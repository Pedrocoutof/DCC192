package com.example.trabalho_pratico.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class homeController {

    @GetMapping("/menu")
    public String menu(HttpSession session){
        String isLogged = (String) session.getAttribute("user");
        System.err.println("IsLogged: " + isLogged);

        if(isLogged != null && !isLogged.isEmpty()){
            return "menu";
        }
        return "redirect:/login";
    }

}
