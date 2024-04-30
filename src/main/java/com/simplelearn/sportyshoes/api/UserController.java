package com.simplelearn.sportyshoes.api;

import com.simplelearn.sportyshoes.exceptions.UserNotFoundException;
import com.simplelearn.sportyshoes.model.User;
import com.simplelearn.sportyshoes.repository.UserRepository;
import com.simplelearn.sportyshoes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAll")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("objectList", users);
        return "customer";
    }

    @GetMapping("/getByUsername")
    public User getUserByUsername(@RequestParam String username) {
        return userService.getUserByUserName(username);
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping("/add")
    public List<User> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/changePassword")
    public List<User> changePassword(@RequestParam int id,
                                     @RequestBody String password) throws UserNotFoundException {
        return userService.changePassword(id, password);
    }

    @PutMapping("/changeRole")
    public List<User> changeRole(@RequestParam int id,
                                 @RequestParam String role) throws UserNotFoundException {
        return userService.changeRole(id, role);
    }

    @DeleteMapping("/delete")
    public List<User> deleteUser(@RequestParam int id) throws UserNotFoundException {
        return userService.deleteUser(id);
    }
}
