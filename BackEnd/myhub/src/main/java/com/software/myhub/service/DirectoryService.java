package com.software.myhub.service;

import com.software.myhub.dto.Directory;
import com.software.myhub.dto.User;
import static com.software.myhub.dto.Directory.prefix_path;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class DirectoryService {
    public static boolean exist(User user, String name) {
        for (Directory repository : user.getStores()) {
            if (repository.getName().equals(name))
                return true;
        }
        return false;
    }

    //新建仓库
    public static String new_store(String username, String name) {
        if (!FormcheckService.checkname(name))
            return "illegal name";
        if (FormcheckService.Searchid(username) == -1)
            return "wrong user name";
        User user = new User(username);
        if (DirectoryService.exist(user, name))
            return "this store already exists";
        String path = user.getUser_path().getPath();
        File new_repository = new File(path + "\\" + name);
        if (new_repository.mkdir()) {
            File logFile = new File(path + "\\" + name + ".log");
            File userFile = new File(path + "\\" + name + ".users");
            try {
                if(logFile.createNewFile())
                {
                    if(userFile.createNewFile())
                        return "success";
                    else return "fail to create user file";
                }
                else
                    return "fail to create log file";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else
            return "fail to add new store";
    }

    //新建文件夹，参数path为相对路径
    public static boolean new_dir(String path, String name) {
        System.out.println("new dir request");
        System.out.println(path);
        System.out.println(name);
        File new_directory = new File(prefix_path + path + "\\" + name);
        String username = path.split("\\\\")[0];
        File log_file = new File(prefix_path + username + "\\" + path.split("\\\\")[1] + ".log");
        if (!new_directory.mkdir())
            return false;
        try (PrintWriter pw = new PrintWriter(new FileWriter(log_file, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            pw.println(username + "->Directory created:" + path + "\\" + name + "->" + sdf.format(date));
            pw.flush();
            pw.close();
            System.out.println("Directory created: " + new_directory.getAbsolutePath());
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while appending \"Directory created: " + new_directory.getAbsolutePath() + "\" to " + log_file.getAbsolutePath());
            e.printStackTrace();
            return false;
        }
    }


}
