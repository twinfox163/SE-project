package com.software.myhub.dto;


import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String username;
    private File user_path;
    private ArrayList<Directory> stores;
    public User(String username) {
        this.username = username;
        this.user_path = new File("C:\\lessons\\2024spring\\software\\project\\new\\file\\" + username);
        stores = new ArrayList<>();
        for (File f : Objects.requireNonNull(user_path.listFiles())) {
            if (f.isDirectory()) {
                stores.add(new Directory(f));
            }
        }
    }

    public File getUser_path() {
        return user_path;
    }

    public void setUser_path(File user_path) {
        this.user_path = user_path;
    }

    public ArrayList<Directory> getStores() {
        return stores;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
