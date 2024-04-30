package com.simplelearn.sportyshoes.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login-page";
    }

    @GetMapping("/home")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/getProducts")
    public String getProducts(){
        return "customer";
    }
}
