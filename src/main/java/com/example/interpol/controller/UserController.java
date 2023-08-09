package com.example.interpol.controller;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.User;
import com.example.interpol.model.UserRole;
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

import static com.example.interpol.model.UserRole.ADMIN;
import static com.example.interpol.model.UserRole.CLIENT;

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
      String path = "authorization";
      if (userService.authenticate(login, password)) {
         User user;
         String admin = "ADMIN";
         String client = "CLIENT";
         try {
            user = userService.findByLogin(login);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userid", user.getId());
            if (admin.equals(user.getRole())) {
               redirectAttributes.addFlashAttribute("message", "You have successfully authorized, " + user.getName()+"!");
               return "redirect:/admin";
            } else if (client.equals(user.getRole())) {
               redirectAttributes.addFlashAttribute("message", "You have successfully authorized, " + user.getName()+"!");
               return "redirect:/client";
            }
         } catch (ServiceException e) {
            e.printStackTrace();
         }
      } else {
         redirectAttributes.addFlashAttribute("errormessage", "Invalid login or password");
         return "redirect:/authorization";
      }
      return path;
   }

   @GetMapping("/client/edit/info/")
   public String showEditForm(HttpServletRequest request, Long id, Model model, RedirectAttributes redirectAttributes) {
      try {
         Long userId = (Long) request.getSession().getAttribute("userid");
         User user = userService.findById(userId);
         model.addAttribute("user", user);
         model.addAttribute("userid", id);
         redirectAttributes.addFlashAttribute("message", "User info was successfully changed");
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
         redirectAttributes.addFlashAttribute("errormessage", "User not changed");
         return "edit_info_client";
      }
      redirectAttributes.addFlashAttribute("message", "The information has been saved successfully");
      return "client_page";
   }


}
