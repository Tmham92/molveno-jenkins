package com.capgemini.molveno.service;

import com.capgemini.molveno.model.PasswordToken;

public interface IPasswordTokenService {
    void save(PasswordToken pToken);

    PasswordToken getPToken(String token);
}
