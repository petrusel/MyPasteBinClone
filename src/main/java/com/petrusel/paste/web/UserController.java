package com.petrusel.paste.web;

import com.petrusel.paste.model.User;
import com.petrusel.paste.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        System.out.println("UserController registration formular");
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") User userRegistration) {
        System.out.println("UserController registration save");
        userService.saveUser(userRegistration);
        return "redirect:/registration?success";
    }

    @GetMapping("/login")
    public String login() {
        System.out.println("UserController login formular");
        return "login";
    }

    @GetMapping("/")
    public String showUserDetails(Model model, Principal principal) {
        String email = principal.getName();
        User loggedUser = userService.getUserByEmail(email);
        model.addAttribute("user", loggedUser);
        System.out.println("UserController afiseaza detalii despre user-ul logat");
        return "user_details";
    }
}
