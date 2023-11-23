package com.example.trabalho_pratico.controllers;

import com.example.trabalho_pratico.middlewares.Middleware;
import com.example.trabalho_pratico.middlewares.isAdmin;
import com.example.trabalho_pratico.models.user.User;
import com.example.trabalho_pratico.models.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("index")
    public String index(HttpSession session, Model model){
        if(!isAdmin.main(session)) {
            return "redirect:/menu";
        }
        List<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/index";
    }

    @PostMapping("delete")
    public String delete(
            Model model,
            @RequestParam(name = "userId") long id
    ){
        userService.deleteUser(id);
        return "redirect:/user/index";
    }
}
