package com.example.interpol.controller;

import com.example.interpol.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showUserForm(Model model) {
        return "index";
    }

    @GetMapping("/error")
    public String showAuthorizationForm( ) {
        return "error";
    }
}
