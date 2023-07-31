package com.example.interpol.controller;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.User;
import com.example.interpol.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {
  private UserServiceImpl userService;

  @Autowired
  public RegistrationController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping("/registration")
  public String showRegistrationPage(Model model) {
    model.addAttribute("user", new User());
    return "registration";
  }

  @PostMapping("/register")
  public String registration(User user, RedirectAttributes redirectAttributes, Model model) {
    try {
      userService.createUser(user);
      redirectAttributes.addFlashAttribute("message", "You have successfully registered!");
      return "redirect:/authorization";
    } catch (ServiceException e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Error message");
      return "registration";
    }
  }
}
