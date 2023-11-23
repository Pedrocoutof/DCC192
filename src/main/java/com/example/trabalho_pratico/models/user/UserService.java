package com.example.trabalho_pratico.models.user;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(long id);
    boolean deleteUser(long id);
    User login(String email, String password) throws Exception;
    List<User> getUsers();
}
