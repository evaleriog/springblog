package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.models.UserRole;
import com.codeup.springblog.repositories.Roles;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private Roles roles;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, Roles roles){
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.roles = roles;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        roles.save(UserRole.roleUser(user));
        return "redirect:/login";
    }
}
