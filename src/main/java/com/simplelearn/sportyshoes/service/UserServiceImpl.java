package com.simplelearn.sportyshoes.service;

import com.simplelearn.sportyshoes.exceptions.UserNotFoundException;
import com.simplelearn.sportyshoes.model.User;
import com.simplelearn.sportyshoes.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.get();
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return userRepository.findAll();
    }

    @Override
    public List<User> changePassword(int id, String password) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(id);
            user.ifPresent(value -> value.setPassword(password));
            userRepository.save(user.get());
            return userRepository.findAll();
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> changeRole(int id, String role) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(id);
            user.ifPresent(value -> value.setRole(role));
            return userRepository.findAll();
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> deleteUser(int id) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(id);
            user.ifPresent(value -> userRepository.delete(value));
            return userRepository.findAll();
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
    @Override
    public User getUserByUserName(String username) {

        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE username=:username", User.class);
        theQuery.setParameter("username", username);
        return theQuery.getSingleResult();
    }
}
