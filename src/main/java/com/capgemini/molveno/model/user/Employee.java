package com.capgemini.molveno.model.user;

import javax.persistence.Entity;

@Entity
public class Employee extends User {

    @Override
    public String getRole() {
        return "Employee";
    }
}
