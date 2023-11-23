package com.example.trabalho_pratico.middlewares;

import com.example.trabalho_pratico.models.user.User;
import jakarta.servlet.http.HttpSession;

public class isAuthenticated extends Middleware {
    public static boolean main(HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        return (sessionUser != null) ? true : false;
    }
}
