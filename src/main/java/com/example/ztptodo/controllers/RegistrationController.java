package com.example.ztptodo.controllers;


import com.example.ztptodo.user.UserService;
import com.example.ztptodo.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.findCredentialsByName(userDto.getUserName()).isPresent()) {
            model.addAttribute("error", "Nazwa użytkownika jest już zajęta");
            return "register";
        }

        userService.registerUserWithDefaultRole(userDto);
        return "redirect:/login";
    }
}