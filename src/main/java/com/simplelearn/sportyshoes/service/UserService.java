package com.simplelearn.sportyshoes.service;

import com.simplelearn.sportyshoes.exceptions.UserNotFoundException;
import com.simplelearn.sportyshoes.model.User;

import java.util.List;

public interface UserService {
        List<User> getAllUsers();
        User getUserById(int id) throws UserNotFoundException;
        List<User> addUser(User user);
        List<User> changePassword(int id, String password) throws UserNotFoundException;
        List<User> changeRole(int id, String role) throws UserNotFoundException;
        List<User> deleteUser(int id) throws UserNotFoundException;
        User getUserByUserName(String username);
}
