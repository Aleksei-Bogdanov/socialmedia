package com.socialmedia.controller;

import com.socialmedia.domain.Role;
import com.socialmedia.domain.User;
import com.socialmedia.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    private UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            User user,
            Model model
    ){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb  != null){
            model.addAttribute("userExist", "User already exists");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
