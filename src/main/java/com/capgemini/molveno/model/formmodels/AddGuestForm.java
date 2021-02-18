package com.capgemini.molveno.model.formmodels;

import javax.validation.constraints.*;

public class AddGuestForm {

    @NotNull @Size(min=1, max=25)
    private String firstName;

    @NotNull @Size(min=1, max=40)
    private String lastName;

    @NotNull
    @Size(min=1, max=40)
    private String streetName;

    @NotNull @NotBlank
    private String houseNumber;

    @NotNull @NotBlank
    private String zipCode;

    @NotNull @Size(min=1, max=40)
    private String city;

    @NotNull @Size(min=1, max=40)
    private String country;

    @NotNull @Size(min=9, max=15)
    private String mobileNumber;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    private String dateOfBirth;

    @NotNull
    private String password;



    boolean emailExists = false;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmailExists() {
        return emailExists;
    }

    public void setEmailExists(boolean emailExists) {
        this.emailExists = emailExists;
    }

}
