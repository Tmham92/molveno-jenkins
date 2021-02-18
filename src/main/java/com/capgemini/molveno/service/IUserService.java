package com.capgemini.molveno.service;

import com.capgemini.molveno.model.details.EnterpriseDetails;
import com.capgemini.molveno.model.details.PersonDetails;
import com.capgemini.molveno.model.formmodels.AddGuestForm;
import com.capgemini.molveno.model.user.Guest;
import com.capgemini.molveno.model.user.User;
import org.springframework.ui.Model;


public interface IUserService {
    void addNewGuest(AddGuestForm form);

    PersonDetails createPersonDetails(AddGuestForm form, User user);

    Guest createGuest(AddGuestForm form);

    Guest createGuestWithEnterpriseDetails(AddGuestForm form, EnterpriseDetails enterpriseDetails);

    EnterpriseDetails setNewEnterpriseDetails(EnterpriseDetails enterpriseDetails);

    Iterable<User> findAll();

    long count();

    void save(User user);

    Model checkIfEnterpriseDetailsAreFilledIn(Model model, EnterpriseDetails enterpriseDetails) throws NullPointerException;

    void addNewUser(AddGuestForm form, EnterpriseDetails enterpriseDetails);

    void addGuestWithEnterprise(AddGuestForm form, EnterpriseDetails enterpriseDetails);

    EnterpriseDetails createEnterpriseDetails(EnterpriseDetails enterpriseDetails);
    User getUserFromId(long id);

    String encodePassword(String newPassword);

}
