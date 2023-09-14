package com.example.trabalho_pratico.models.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.security.MessageDigest;
import java.util.regex.Pattern;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private void verifyEmail(String email){
        try{
            Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            if(emailPattern.matcher(email).matches()){
                this.email = email;
            }
        }
        catch (Exception e){

        }
    }

    public User(String name, String email, String password){

        this.verifyEmail(email);

        this.name = name;
        this.email = email;

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Calcule o hash da senha
            byte[] hashedBytes = md.digest(password.getBytes());

            // Converta o hash para uma representação hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            this.password = sb.toString();
        }catch(Exception e){
            return;
        }

    }

    public User() {}

    public String generatePassword(String password) throws Exception {
        try{
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

            return messageDigest.toString();
        }
        catch(Exception e){
            return null;
        }
    }

    public static boolean verifyPassword(String password){
        return false;
    }
}
