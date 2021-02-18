package com.capgemini.molveno.service;

import com.capgemini.molveno.model.formmodels.AddGuestForm;
import com.capgemini.molveno.model.user.Guest;
import com.capgemini.molveno.repository.EnterpriseDetailsRepository;
import com.capgemini.molveno.repository.PersonDetailsRepository;
import com.capgemini.molveno.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class UserServiceTest {

    private UserService userServiceTest;
    private final AddGuestForm addGuestForm = new AddGuestForm();
    private final UserRepository userRepositoryTest = mock(UserRepository.class);
    private final PersonDetailsRepository personDetailsRepositoryTest = mock(PersonDetailsRepository.class);
    private final EnterpriseDetailsRepository enterpriseDetailsRepositoryTest = mock(EnterpriseDetailsRepository.class);


    @BeforeEach
    void setUp() {
        userServiceTest = new UserService(userRepositoryTest, personDetailsRepositoryTest, enterpriseDetailsRepositoryTest);
        addGuestForm.setFirstName("Tobias");
        addGuestForm.setLastName("Ham");
        addGuestForm.setEmail("tmham92@gmail.com");
        addGuestForm.setPassword("marijn1");
        addGuestForm.setDateOfBirth("18-11-1992");
        addGuestForm.setStreetName("Gorechtkade");
        addGuestForm.setHouseNumber("39B");
        addGuestForm.setCity("Groningen");
        addGuestForm.setCountry("Netherlands");
        addGuestForm.setZipCode("9713BE");
        addGuestForm.setMobileNumber("0650552677");
    }

    @Test
    public void createGuestTest() {
        Guest guest = userServiceTest.createGuest(addGuestForm);
        assertEquals(guest.getFirstName(), "Tobias");
    }

}