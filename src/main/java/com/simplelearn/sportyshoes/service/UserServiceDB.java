package com.simplelearn.sportyshoes.service;

import com.simplelearn.sportyshoes.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceDB extends UserDetailsService {

    public User findByUsername(String username);
}
