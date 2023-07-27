package com.example.interpol.controller;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.User;
import com.example.interpol.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class UserController {

    private UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping("/users/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user, RedirectAttributes redirectAttrs, Model model) {
        try {
            userService.createUser(user);
        } catch (ServiceException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "Error message");
            return "redirect:/registration";
        }
        return "redirect:/authorization";
    }

    @GetMapping("/authorization")
    public String showAuthorizationForm(Model model) {
        model.addAttribute("user", new User());
        return "authorization";
    }

    @PostMapping("/authorize")
    public String authorizationUser(User user, RedirectAttributes redirectAttributes) {
        //todo
        return "redirect:/users";
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

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            return "edit_user_form";
        } catch (ServiceException e) {
            return "redirect:/users";
        }
    }

    @PostMapping("/users/update")
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(user);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        redirectAttributes.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("message", "The user (id=" + id + ") has been successfully deleted");
        return "redirect:/users";
    }


}
