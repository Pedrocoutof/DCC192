package com.example.trabalho_pratico.controllers.auth;

import com.example.trabalho_pratico.models.user.User;
import com.example.trabalho_pratico.models.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import static com.example.trabalho_pratico.config.Constants.SESSION_TIMEOUT;

@Controller
@SessionAttributes("user")
public class Login {

    private UserService userService;

    public Login(UserService userService){
        this.userService = userService;
    }

    private User auth(String email, String password) throws Exception {
        System.err.println("Email recebido: " + email);
        System.err.println("Password recebido: " + password);


        return userService.login(email, password);
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        return "auth/login";
    }

    /* TODO
        Conferir pois só estou armazenando o email em sessão
     */
    @PostMapping("/login")
    public String loginPost(
            @RequestParam(name = "email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model
    ) throws Exception {

        User authenticatedUser = this.auth(email, password);

        if(authenticatedUser  != null){
            session.setAttribute("user", authenticatedUser.getEmail());
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
    public String registerPost(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model){

        User user = new User(name, email, password);

        userService.createUser(user);

        return "redirect:/login";
    }
}
