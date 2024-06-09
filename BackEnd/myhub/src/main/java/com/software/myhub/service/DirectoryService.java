package com.software.myhub.service;

import com.software.myhub.dto.Directory;
import com.software.myhub.dto.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class DirectoryService {
    public static boolean exist(User user, String name) {
        for (Directory repository : user.getStores()) {
            if (repository.getName().equals(name))
                return true;
        }
        return false;
    }

    public static String new_store(String username, String name) {
        if (!FormcheckService.checkname(name))
            return "illegal name";
        if (FormcheckService.Searchid(username) == -1)
            return "wrong user name";
        User user = new User(username);
        if (DirectoryService.exist(user, name))
            return "this store already exists";
        String path = user.getUser_path().getPath();
        if (DirectoryService.create(path, name, true)) {
            return "success";
        } else
            return "fail to add new store";
    }

    public static boolean create(String path, String name, boolean store_tag) {
        File new_directory = new File(path + "\\" + name);
        File log_file = new File(path + "\\" + name + "\\" + name + ".log");
        if (!new_directory.mkdir())
            return false;
        if (store_tag) {
            try {
                return log_file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(log_file, true))) {
                pw.println("Directory created: " + new_directory.getAbsolutePath());
                System.out.println("Directory created: " + new_directory.getAbsolutePath());
                return true;
            } catch (IOException e) {
                System.out.println("An error occurred while appending \"Directory created: " + new_directory.getAbsolutePath() + "\" to " + log_file.getAbsolutePath());
                e.printStackTrace();
                return false;
            }
        }
    }


}
