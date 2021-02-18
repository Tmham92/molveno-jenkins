package com.capgemini.molveno.model.details;

import com.capgemini.molveno.model.user.Guest;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EnterpriseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long enterpriseId;

    @ManyToMany
    private Set<Guest> contactPersons;

    private String enterpriseName;
    private String enterpriseCoC;

    public EnterpriseDetails(long enterpriseId, String companyName, String companyTaxId) {
    }

    public EnterpriseDetails() {
    }

    public EnterpriseDetails(String enterpriseName, String enterpriseCoC) {
        this.enterpriseName = enterpriseName;
        this.enterpriseCoC = enterpriseCoC;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseCoC() {
        return enterpriseCoC;
    }

    public void setEnterpriseCoC(String enterpriseCoC) {
        this.enterpriseCoC = enterpriseCoC;
    }

    public Set<Guest> getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(Set<Guest> contactPersons) {
        this.contactPersons = contactPersons;
    }

    @Override
    public String toString() {
        return "EnterpriseDetails{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseCoC='" + enterpriseCoC + '\'' +
                '}';
    }
}
