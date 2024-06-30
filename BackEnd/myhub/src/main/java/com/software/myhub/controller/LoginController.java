package com.software.myhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.software.myhub.service.*;
@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(String username, String password) {
        System.out.println("get login request.\n");
        System.out.println("username: " + username + "\n");
        System.out.println("password: " + password + "\n");
        return LoginService.login(username, password);
    }

    @RequestMapping("/signup")
    @ResponseBody
    public String signup(String username, String password) {
        System.out.println("get signup request.\n");
        System.out.println("username: " + username + "\n");
        System.out.println("password: " + password + "\n");
        return LoginService.signup(username, password);
    }
}
