package com.software.myhub.controller;


import com.software.myhub.dto.*;
import com.software.myhub.dto.User;
import com.software.myhub.service.DirectoryService;
import com.software.myhub.service.FollowService;
import com.software.myhub.service.FormcheckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;

@Controller
public class HomeController extends DirectoryService {

    @RequestMapping("/stores")
    @ResponseBody
    public ArrayList<Store> getStores(String username) {
        if(FormcheckService.Searchid(username) == -1)
            return null;
        User user = new User(username);
        return user.getStores();
    }

    @RequestMapping("/new_store")
    @ResponseBody
    public String newStore(String username, String name) {
        System.out.println("new store request");
        System.out.println(username);
        System.out.println(name);
        return DirectoryService.new_store(username, name);
    }

    @RequestMapping("/follow_list")
    @ResponseBody
    public ArrayList<Store> followList(String username) {
        return FollowService.followList(username);
    }

    @RequestMapping ("/follow")
    @ResponseBody
    public String follow(@RequestParam("username") String username, @RequestParam("store") String directory) {
        System.out.println("follow request");
        System.out.println(username);
        System.out.println(directory);
        Store store = new Store(new File(Directory.prefix_path + directory.replace("->","\\")));
        return FollowService.follow(username, store);
    }

    @RequestMapping("/search_user")
    @ResponseBody
    public User searchUser(String username) {
        if(username == null)
            return null;
        User user = new User(username);
        if(user.getUsername() == null)
            return null;
        else
            return user;
    }

    @RequestMapping("/search_repo")
    @ResponseBody
    public Store searchRepo(String username, String name) {
        if(username == null)
            return null;
        User user = new User(username);
        if(user.getUsername() == null)
            return null;
        for (Store store : user.getStores()) {
            if(store.getName().equals(name))
                return store;
        }
        return null;
    }
}
