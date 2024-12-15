package com.example.ztptodo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (isValidUser(username, password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    private boolean isValidUser(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }
}