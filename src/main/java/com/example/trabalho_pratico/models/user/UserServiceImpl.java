package com.example.trabalho_pratico.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        try{
            return userRepository.save(user);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    private User findByEmail(String email){
        List<User> userList =  userRepository.findAll();

        for(User user : userList){
            if(user.getEmail().equals(email)){
                return user;
            }
        }

        return null;
    }

    // Função para verificar a senha
    public static boolean verifyPassword(String userInputPassword, String hashedPassword) throws NoSuchAlgorithmException {
        // Crie o objeto MessageDigest com o algoritmo SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Calcule o hash da senha inserida pelo usuário
        byte[] hashedBytes = md.digest(userInputPassword.getBytes());

        // Compare o hash calculado com o hash armazenado
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString().equals(hashedPassword);
    }

    // Função que retorna todos os usuários
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    @Override
    public boolean deleteUser(long id) {
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public User login(String email, String password) throws Exception {
        User user = findByEmail(email);

        if(user != null){
            if(verifyPassword(password, user.getPassword())){
                return user;
            }
        }
        return null;
    }
}
