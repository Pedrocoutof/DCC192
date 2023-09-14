package com.example.trabalho_pratico.models.user;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    User createUser(User user);
    User getUserById(long id);
    User login(String email, String password) throws Exception;
}
