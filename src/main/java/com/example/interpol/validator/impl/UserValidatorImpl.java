package com.example.interpol.validator.impl;

import com.example.interpol.model.User;
import com.example.interpol.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
//
//    public static final String LOGIN_REGEX = "^[a-zA-Z0-9]{4,20}$";
//    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
//    public static final String NAME_REGEX = "^[a-zA-Z]{2,15}$";
//    public static final String SURNAME_REGEX = "^[a-zA-Z]{2,30}$";
//    public static final String PHONE_REGEX = "^\\s*\\+?375((25|29|33|44)\\d{7})\\s*$";
//    public static final String PASSWORD_REGEX = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,30}";
//
//
//
    @Override
    public boolean isValidUser(User user) {
//        return user.getName() != null && user.getName().matches(NAME_REGEX) &&
//                user.getSurname() != null && user.getSurname().matches(SURNAME_REGEX) &&
//                user.getPhone() != null && user.getPhone().matches(PHONE_REGEX) &&
//                user.getEmail() != null && user.getEmail().matches(EMAIL_REGEX);
        return true;
    }

    @Override
    public boolean isValidLogin(String login) {
        return true;
    }

    @Override
    public boolean isValidPassword(String password) {
        return true;
    }
//
//    @Override
//    public boolean isValidLogin(String login) {
//        return login != null && login.matches(LOGIN_REGEX);
//    }
//
//    @Override
//    public boolean isValidPassword(String password) {
//        return password != null && password.matches(PASSWORD_REGEX);
//    }
//
//    public boolean isValidEmail(String email) {
//        return email != null && email.matches(EMAIL_REGEX);
//    }
//
//    public boolean isValidName(String name) {
//        return name != null && name.matches(NAME_REGEX);
//    }
//
//    public boolean isValidSurname(String surname) {
//        return surname != null && surname.matches(SURNAME_REGEX);
//    }
//
//    public boolean isValidPhone(String phone) {
//        return phone != null && phone.matches(PHONE_REGEX);
//    }


}
