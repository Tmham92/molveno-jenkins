package com.capgemini.molveno.model.details;

import javax.persistence.*;

@Entity
public class PersonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long guestId;
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;

    private String mobileNumber;
    private String email;

    public PersonDetails() {
    }

    public static class Builder {

        private final long guestId;
        private String streetName;
        private String houseNumber;
        private String zipCode;
        private String city;
        private String country;

        private String mobileNumber;
        private String email;


        public Builder(long guestId) {
            this.guestId = guestId;
        }

        public Builder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withMobile(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public PersonDetails build() {
            PersonDetails personDetails = new PersonDetails();
            personDetails.guestId = this.guestId;
            personDetails.streetName = this.streetName;
            personDetails.houseNumber = this.houseNumber;
            personDetails.city = this.city;
            personDetails.country = this.country;
            personDetails.mobileNumber = this.mobileNumber;
            personDetails.email = email;
            personDetails.zipCode = zipCode;
            return personDetails;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guest) {
        this.guestId = guest;
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


    @Override
    public String toString() {
        return "PersonDetails{" +
                "id=" + id +
                ", guestId=" + guestId +
                ", streetName='" + streetName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
