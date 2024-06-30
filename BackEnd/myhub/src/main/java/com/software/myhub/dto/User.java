package com.software.myhub.dto;


import com.software.myhub.service.FormcheckService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

import static com.software.myhub.dto.Directory.prefix_path;

public class User {
    private String username;
    private File user_path;
    private ArrayList<Store> stores;
    private File followFile;

    public User(String username) {
        if(FormcheckService.Searchid(username) == -1)
        {
            this.username = null;
            this.user_path = null;
            this.stores = null;
            this.followFile = null;
        }
        else {
            this.username = username;
            this.user_path = new File("C:\\lessons\\2024spring\\software\\project\\new\\file\\" + username);
            this.stores = new ArrayList<>();
            for (File f : Objects.requireNonNull(user_path.listFiles())) {
                if (f.isDirectory()) {
                    this.stores.add(new Store(f));
                }
            }
            this.followFile = new File(prefix_path + username + "\\follow_list.txt");
        }
    }

    public File getUser_path() {
        return user_path;
    }

    public void setUser_path(File user_path) {
        this.user_path = user_path;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public File getFollowFile() {
        return followFile;
    }

    public boolean addFollow(Store store) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(followFile, true))){
            pw.println(store.getDirectory());
            pw.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
