package com.software.myhub.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static boolean upload(MultipartFile file, String path, boolean modify) {
        if (!path.contains("->"))
            return false;
        String fileName = file.getOriginalFilename();
        if(fileName == null)
            return false;
        String path_decode = "C:\\lessons\\2024spring\\software\\project\\new\\file\\" + path.replace("->", "\\");
        try {
            // 创建目标文件夹
            File uploadDir = new File(path_decode);
            if (!uploadDir.exists()) {
                if(!uploadDir.mkdirs())
                    return false;
            }
            // 保存文件到指定路径
            File targetFile = new File(uploadDir, fileName);
            if(targetFile.exists() && !modify)
                return false;
            file.transferTo(targetFile);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
