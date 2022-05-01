package com.crickinfo.crickinfo.controller;

import com.crickinfo.crickinfo.dto.UserDto;
import com.crickinfo.crickinfo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") UserDto userDto) {
        userService.save(userDto);
        return "redirect:/registration?success";
    }
}
