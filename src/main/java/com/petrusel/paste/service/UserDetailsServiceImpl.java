package com.petrusel.paste.service;

import com.petrusel.paste.model.User;
import com.petrusel.paste.repository.UserRepository;
import com.petrusel.paste.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found!");
        }
        System.out.println("UserDetailsServiceImpl se returneaza pentru login un obiect de tip User prin loadUserByUsername()");
        return new UserRegistrationDto(user);
    }
}
