package com.example.interpol.controller;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.User;
import com.example.interpol.service.UserService;
import com.example.interpol.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/users/save")
    public String createUser(User user, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message", "The user has been create successfully");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/users";
    }

    @GetMapping("/client")
    public String showClientPage(Model model) {
        return "client_page";
    }

    @GetMapping("/authorization")
    public String showAuthorizationForm(Model model) {
        model.addAttribute("user", new User());
        return "authorization";
    }

    @PostMapping("/authorize")
    public String authorizationUser(@RequestParam String login, @RequestParam String password, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (userService.authenticate(login, password)) {
            User user;
            try {
                user = userService.findByLogin(login);
                request.getSession().setAttribute("user", user);
                return "redirect:/client";
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        } else {
            redirectAttributes.addAttribute("errormessage", "Invalid login or password");
            return "redirect:/authorization";
        }
    }

    @GetMapping("/client/edit/info/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            return "edit_info_client";
        } catch (ServiceException e) {
            return "redirect:/";
        }
    }

    @PostMapping("/client/info/update")
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUser(user);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        redirectAttributes.addFlashAttribute("message", "The information has been saved successfully");
        return "client_page";
    }


}
