package com.example.authorbook.controller;


import com.example.authorbook.entity.User;
import com.example.authorbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User user,
                            @RequestParam("image") MultipartFile multipartFile){
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if(byEmail.isEmpty()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user, multipartFile);
        }
        return "redirect:/";
    }
}
