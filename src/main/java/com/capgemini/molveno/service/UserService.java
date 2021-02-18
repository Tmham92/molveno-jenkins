package com.capgemini.molveno.service;

import com.capgemini.molveno.model.details.EnterpriseDetails;
import com.capgemini.molveno.model.details.PersonDetails;
import com.capgemini.molveno.model.formmodels.AddGuestForm;
import com.capgemini.molveno.model.user.Guest;
import com.capgemini.molveno.model.user.User;
import com.capgemini.molveno.repository.EnterpriseDetailsRepository;
import com.capgemini.molveno.repository.PersonDetailsRepository;
import com.capgemini.molveno.repository.UserRepository;
import com.capgemini.molveno.utility.FormDateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PersonDetailsRepository personDetailsRepository;
    private final EnterpriseDetailsRepository enterpriseDetailsRepository;

    @Autowired
    public UserService(UserRepository userRepository, PersonDetailsRepository personDetailsRepository,
                       EnterpriseDetailsRepository enterpriseDetailsRepository) {
        this.userRepository = userRepository;
        this.personDetailsRepository = personDetailsRepository;
        this.enterpriseDetailsRepository = enterpriseDetailsRepository;
    }

    @Override
    public void addNewGuest(AddGuestForm form) {
        Guest guest = createGuest(form);
        PersonDetails personDetails = createPersonDetails(form, guest);
        guest.setDetails(personDetails);


        // loginUserRepository.save(createLoginUser(form.getEmail(), encodePassword(form.getPassword())));
        userRepository.save(guest);
        System.out.println("Created a new Guest.");
        // add login user ID to guest
    }

    @Override
    public PersonDetails createPersonDetails(AddGuestForm form, User user) {
        PersonDetails personDetails = new PersonDetails.Builder(user.getId())
                .withStreetName(form.getStreetName())
                .withHouseNumber(form.getHouseNumber())
                .withZipCode(form.getZipCode())
                .withCity(form.getCity())
                .withCountry(form.getCountry())
                .withMobile(form.getMobileNumber())
                .build();
        personDetails.setId(personDetailsRepository.count()+1);
        personDetailsRepository.save(personDetails);
        return personDetails;
    }

    @Override
    public Guest createGuest(AddGuestForm form) {
        Guest guest = new Guest();
        guest.setId(userRepository.count()+1);
        guest.setFirstName(form.getFirstName());
        guest.setLastName(form.getLastName());
        guest.setEmail(form.getEmail());
        guest.setDateOfBirth(FormDateParser.convertDateStringToDate(form.getDateOfBirth()));
        guest.setCreationDate(LocalDateTime.now());
        guest.setPassword(encodePassword(form.getPassword()));
        return guest;
    }

    @Override
    public Guest createGuestWithEnterpriseDetails(AddGuestForm form, EnterpriseDetails enterpriseDetails) {
        Guest guest = createGuest(form);
        guest.getEnterpriseDetails().add(enterpriseDetails);
        guest.setId(userRepository.count());
        return guest;
    }


    @Override
    public EnterpriseDetails setNewEnterpriseDetails(EnterpriseDetails enterpriseDetails) {
        if (enterpriseDetails.getEnterpriseName().equals("") || enterpriseDetails.getEnterpriseCoC().equals("")) {
            enterpriseDetails = new EnterpriseDetails();
        }
        return enterpriseDetails;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    public String encodePassword(String password) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public Model checkIfEnterpriseDetailsAreFilledIn(Model model, EnterpriseDetails enterpriseDetails) throws NullPointerException {
        try {
            if (enterpriseDetails.getEnterpriseName() != null) {
                model.addAttribute("enterpriseDetails", new EnterpriseDetails());
            } else {
                model.addAttribute("enterpriseDetails", enterpriseDetails);
            }
        } catch (NullPointerException e) {
            model.addAttribute("enterpriseDetails", new EnterpriseDetails());
        }
        return model;
    }


    @Override
    public void addNewUser(AddGuestForm form, EnterpriseDetails enterpriseDetails) {
        System.out.println(enterpriseDetails.toString());
        if (enterpriseDetails.getEnterpriseName() ==  null) {
            addNewGuest(form);
        } else {
            addGuestWithEnterprise(form, enterpriseDetails);
        }
    }

    @Override
    public void addGuestWithEnterprise(AddGuestForm form, EnterpriseDetails enterpriseDetails) {
        Guest guestWithEnterprise = createGuestWithEnterpriseDetails(form, createEnterpriseDetails(enterpriseDetails));
        userRepository.save(guestWithEnterprise);
        System.out.println("Created a new Guest with Enterprise.");
    }

    @Override
    public EnterpriseDetails createEnterpriseDetails(EnterpriseDetails enterpriseDetails) {
        enterpriseDetails.setEnterpriseId(enterpriseDetailsRepository.count() + 1);
        enterpriseDetailsRepository.save(enterpriseDetails);
        System.out.println("Created new EnterpriseDetails.");
        return enterpriseDetails;
    }


    public boolean checkForEmailDuplicate(String email) {
        return getUserFromEmail(email) != null;
    }

    public User getUserFromEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getUserFromId(long id){return userRepository.findUserById(id);}
}
