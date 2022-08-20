package com.petrusel.paste.service;

import com.petrusel.paste.model.User;
import com.petrusel.paste.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
