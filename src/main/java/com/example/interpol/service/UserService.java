package com.example.interpol.service;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.User;

import java.util.List;


public interface UserService {
     User createUser(User user) throws ServiceException;

     void deleteUser(Long userId);

     List<User> findAll();

    User findById(Long id) throws ServiceException;

}


