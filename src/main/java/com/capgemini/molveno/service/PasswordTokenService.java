package com.capgemini.molveno.service;

import com.capgemini.molveno.model.PasswordToken;
import com.capgemini.molveno.repository.PasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordTokenService implements IPasswordTokenService{

    private PasswordTokenRepository ptr;
    
    @Autowired
    public PasswordTokenService(PasswordTokenRepository ptr){
        this.ptr = ptr;
    }

    public void save(PasswordToken pToken){
        ptr.save(pToken);
    }

    public PasswordToken getPToken(String token) {
        if (ptr.findPasswordTokenByToken(token) != null){
            return ptr.findPasswordTokenByToken(token);
        }
        else return null;
    }
}
