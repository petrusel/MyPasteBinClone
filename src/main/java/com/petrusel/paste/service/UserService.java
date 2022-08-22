package com.petrusel.paste.service;

import com.petrusel.paste.model.User;
import com.petrusel.paste.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User userRegistration) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(userRegistration.getFirstName(), userRegistration.getLastName(),
                userRegistration.getEmail(), passwordEncoder.encode(userRegistration.getPassword()));
        System.out.println("UserService se salveaza userul la inregistrare");
        userRepository.save(user);
    }

    public User getUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        System.out.println("UserService este returnat un user in functie de email");
        return user;
    }
}
