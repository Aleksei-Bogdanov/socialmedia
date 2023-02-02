package com.socialmedia.controller;

import com.socialmedia.domain.Role;
import com.socialmedia.domain.User;
import com.socialmedia.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String userList(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "userList";
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editList(
            @AuthenticationPrincipal User user,
            Model model
    ){
        model.addAttribute("user", userService.loadUserByUsername(user.getUsername()));
        model.addAttribute("roles", Role.values());
        return "editList";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String saveUser(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam ("userId") User user
    ){
        userService.saveUser(username, form, user);
        return "redirect:/users";
    }

}
