package com.software.myhub.service;

import com.software.myhub.dto.History;
import com.software.myhub.dto.Store;
import com.software.myhub.dto.User;
import org.springframework.stereotype.Service;

import static com.software.myhub.dto.Directory.prefix_path;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Service
public class FollowService {
    public static ArrayList<Store> followList(String username) {
        ArrayList<Store> fl = new ArrayList<>();
        File followFile = new File(prefix_path + username + "\\follow_list.txt");
        if (followFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(followFile));
                String line;
                File storeFile;
                while ((line = br.readLine()) != null) {
                    storeFile = new File(prefix_path + line.replace("->", "\\"));
                    if (storeFile.exists()) {
                        fl.add(new Store(storeFile));
                    }
                }
                br.close();
                return fl;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            //创建关注文件
            //关注文件每行格式: username->repository_name   （username为该仓库拥有者id）
            try {
                if (followFile.createNewFile())
                    return fl;
                else return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String follow(String username, Store store) {
        if(username == null || store == null)
            return null;
        User user = new User(username);
        for(Store followed : Objects.requireNonNull(followList(username))) {
            if(followed.getDirectory().equals(store.getDirectory())) {
                System.out.println("already followed");
                return "already followed";
            }
        }
        if (user.addFollow(store) && store.tobeFollowed(username))
            return "success";
        else return "fail";
    }
}
