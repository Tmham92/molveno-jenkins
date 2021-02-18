package com.capgemini.molveno.service;

import com.capgemini.molveno.model.PasswordToken;

public interface ILoginService {
    void sendEmail(String email, PasswordToken token);
}
