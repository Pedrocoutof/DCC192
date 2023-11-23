package com.example.trabalho_pratico.middlewares;

import com.example.trabalho_pratico.models.user.User;
import jakarta.servlet.http.HttpSession;

public class isAdmin extends Middleware {
    public static boolean main(HttpSession session) {
        if(isAuthenticated.main(session)) {
            User curUser = (User) session.getAttribute("user");
            return curUser.getAdmin() == 1 ? true : false;
        }
        return false;
    }
}
