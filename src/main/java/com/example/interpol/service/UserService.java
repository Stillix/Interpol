package com.example.interpol.service;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {
     User createUser(User user) throws ServiceException;

    boolean authenticate(String login, String password);

    User updateUser(User user) throws ServiceException;

  void deleteUser(Long userId);

     List<User> findAll();

    User findById(Long id) throws ServiceException;

    User findByLogin(String login) throws ServiceException;
}


