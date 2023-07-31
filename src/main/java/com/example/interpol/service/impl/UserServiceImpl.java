package com.example.interpol.service.impl;

import com.example.interpol.exception.ServiceException;
import com.example.interpol.model.User;
import com.example.interpol.repository.UserRepository;
import com.example.interpol.service.UserService;
import com.example.interpol.validator.UserValidator;
import com.example.interpol.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private UserRepository userRepository;
    private UserValidator userValidator = new UserValidatorImpl();
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) throws ServiceException {
        Optional<User> existingUser = userRepository.findByLogin(user.getLogin());
        if (existingUser.isPresent()) {
            throw new ServiceException("User with this login already exists");
        }
        if (userValidator.isValidUser(user)) {
            String userPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(userPassword);
            return userRepository.save(user);
        }

        return user;
    }

    @Override
    public boolean authenticate(String login, String password) {
        boolean match = false;
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent()) {
            if (userValidator.isValidLogin(login) && userValidator.isValidPassword(password)) {
                match = passwordEncoder.matches(password, user.get().getPassword());
            }
        }
        return match;
    }

    @Override
    public User updateUser(User user) throws ServiceException {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findById(Long id) throws ServiceException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new ServiceException("Failed find user by id");
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent()) {
            return user.get();
        }
        throw new ServiceException("Failed find user by login");
    }

}
