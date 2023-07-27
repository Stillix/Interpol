package com.example.interpol.validator;

import com.example.interpol.model.User;

public interface UserValidator {


    boolean isValidUser(User user);

    boolean isValidLogin(String login);

    boolean isValidPassword(String password);
}
