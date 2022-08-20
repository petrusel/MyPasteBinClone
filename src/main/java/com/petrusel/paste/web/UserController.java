package com.petrusel.paste.web;

import com.petrusel.paste.model.User;
import com.petrusel.paste.service.UserServiceImpl;
import com.petrusel.paste.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userServiceImpl.save(registrationDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String showUserDetails(Model model, Principal principal) {
        String un = principal.getName();
        User loggedUser = userServiceImpl.loadUserByEmail(un);
        model.addAttribute("user", loggedUser);
        return "user_details";
    }
}
