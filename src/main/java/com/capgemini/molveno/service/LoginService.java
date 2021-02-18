package com.capgemini.molveno.service;

import com.capgemini.molveno.model.PasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService{

    private JavaMailSender javaMailSender;
    
    @Autowired
    public LoginService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String email, PasswordToken token){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Molveno Password Reset");
        msg.setText("Please navigate to the following link to reset your password: http://localhost:8080/api/users/changepassword?token="+ token.getToken());
        System.out.println("email sent in service");
        javaMailSender.send(msg);
    }


}
