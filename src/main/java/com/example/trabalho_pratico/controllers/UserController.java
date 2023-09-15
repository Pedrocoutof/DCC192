package com.example.trabalho_pratico.controllers;

import com.example.trabalho_pratico.models.user.User;
import com.example.trabalho_pratico.models.user.UserRepository;
import com.example.trabalho_pratico.models.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("index")
    public String index(Model model){
        List<User> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/index";
    }
}
