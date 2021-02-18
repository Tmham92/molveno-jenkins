package com.capgemini.molveno.model;

import com.capgemini.molveno.model.user.User;

import javax.persistence.*;

@Entity
public class PasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    private String token;

    public PasswordToken(){
    }

    public PasswordToken(User user){
        this.user = user;
        this.token = generateToken();
    }

    private String generateToken(){
        String charsString = "abcdefghijklmnopqrstuvwxyz0123456789";
        String token = "";
        for (int i = 0; i < 15; i++) {
            int random = (int) (Math.random() * 36);
            token += String.valueOf(charsString.charAt(random));
        }
        System.out.println(token);
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
