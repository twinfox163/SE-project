package com.software.myhub.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.software.myhub.dto.Directory.prefix_path;

@Service
public class FileService {
    public static ResponseEntity<Resource> readFile(String filename) {
        try {
            System.out.println("Reading file: " + filename);
            String decodedFilename = URLDecoder.decode(filename.replace("->", "\\"), "UTF-8");
            Path filePath = Paths.get("C:\\lessons\\2024spring\\software\\project\\new\\file").resolve(decodedFilename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            String mimeType = Files.probeContentType(filePath);

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, mimeType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public static boolean upload(MultipartFile file, String path, boolean modify, String mark) {
        if (!path.contains("->"))
            return false;
        String fileName = file.getOriginalFilename();
        if(fileName == null)
            return false;
        path = path.replace("->", "\\");
        String username = path.split("\\\\")[0];
        File log_file = new File(prefix_path + username + "\\" + path.split("\\\\")[1] + ".log");
        String path_decode = prefix_path + path;
        try {
            // 创建目标文件夹
            File uploadDir = new File(path_decode);
            if (!uploadDir.exists()) {
                return false;
            }
            // 保存文件到指定路径
            File targetFile = new File(uploadDir, fileName);
            String newFileName;
            int i = 1;
            if(!modify)
                while(targetFile.exists())
                {
                    if(i > 32)
                        return false;
                    newFileName = fileName + "(" + i + ")";
                    targetFile = new File(uploadDir, newFileName);
                    i++;
                }

            try (PrintWriter pw = new PrintWriter(new FileWriter(log_file, true))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                if(modify)
                {
                    if(mark == null)
                    {
                        pw.println(username + "->File modified:" + path + "\\" + fileName + "->" + sdf.format(date));
                        System.out.println(username + "->File modified:" + path + "\\" + fileName + "->" + sdf.format(date));
                    }
                    else {
                        pw.println(username + "->File modified:" + path + "\\" + fileName + "    mark:" + mark + "->" + sdf.format(date));
                        System.out.println(username + "->File modified:" + path + "\\" + fileName + "->" + sdf.format(date));
                    }
                }
                else
                {
                    pw.println(username + "->File upload:" + path + "\\" + fileName + "->" + sdf.format(date));
                    System.out.println(username + "->File modified:" + path + "\\" + fileName + "->" + sdf.format(date));
                }
                pw.flush();
            } catch (IOException e) {
                System.out.println("An error occurred while appending \"File upload/modified: " + path + "\\" + fileName + "\" to " + log_file.getAbsolutePath());
                e.printStackTrace();
                return false;
            }
            file.transferTo(targetFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean fileDelete(String path) {
        path = path.replace("->", "\\");
        File file = new File(prefix_path + path);
        if (file.exists()) {
            String username = path.split("\\\\")[0];
            File log_file = new File(prefix_path + username + "\\" + path.split("\\\\")[1] + ".log");
            try (PrintWriter pw = new PrintWriter(new FileWriter(log_file, true))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                pw.println(username + "->File deleted:" + path + "->" + sdf.format(date));
                System.out.println(username + "->File deleted:" + path + "->" + sdf.format(date));
                pw.flush();
            }
            catch (IOException e) {
                return false;
            }
            return file.delete();
        }
        else
            return false;
    }
}
