package com.capgemini.molveno.model.formmodels;

import javax.validation.constraints.NotEmpty;

public class NewPasswordForm {

    @NotEmpty
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
