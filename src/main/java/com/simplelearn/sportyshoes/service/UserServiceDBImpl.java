package com.simplelearn.sportyshoes.service;

import com.simplelearn.sportyshoes.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceDBImpl implements UserServiceDB{

    @Autowired
    private UserServiceImpl userService;


    @Override
    public User findByUsername(String username) {
        // check db if user exists
        return userService.getUserByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return org.springframework.security.core.userdetails.User
                .builder().username(user.getUsername()).password(user.getPassword()).roles(getRoles(user)).build();
    }

    public String[] getRoles(User user) {
        if(user.getRole()==null) {
            return new String[] {"CUSTOMER"};
        }else {
            return user.getRole().split(",");
        }
    }


}
