package com.software.myhub.controller;


import com.software.myhub.dto.*;
import com.software.myhub.dto.User;
import com.software.myhub.service.DirectoryService;
import com.software.myhub.service.FormcheckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping("/stores")
    @ResponseBody
    public ArrayList<Directory> getStores(String username) {
        if(FormcheckService.Searchid(username) == -1)
            return null;
        User user = new User(username);
        return user.getStores();
    }

    @RequestMapping("/new_store")
    @ResponseBody
    public String newStore(String username, String name) {
        return DirectoryService.new_store(username, name);
    }
}
