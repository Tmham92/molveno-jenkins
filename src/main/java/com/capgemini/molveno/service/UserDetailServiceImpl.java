package com.capgemini.molveno.service;

import com.capgemini.molveno.model.user.MyUserDetails;
import com.capgemini.molveno.model.user.User;
import com.capgemini.molveno.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("No account found for this email address.");
        }

        return new MyUserDetails(user);
    }

}
