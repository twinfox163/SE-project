package com.software.myhub.controller;

import com.software.myhub.service.DirectoryService;
import com.software.myhub.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @RequestMapping("/new_dir")
    @ResponseBody
    public String newDir(String path, String dirname) {
        System.out.println("new dir request");
        System.out.println(path);
        System.out.println(dirname);
        if (path == null || path.isEmpty() || dirname == null || dirname.isEmpty())
            return "illegal argument";
        if (!path.contains("->"))
            return "illegal path";
        String path_decode = path.replace("->", "\\");
        if (DirectoryService.new_dir(path_decode, dirname))
            return "success";
        else
            return "dir error";
    }

    @GetMapping("/files")
    public ResponseEntity<Resource> showFile(String filename) {
        return FileService.readFile(filename);
    }


    @PostMapping("/upload")
    public boolean handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        System.out.println("upload request");
        System.out.println(path);
        System.out.println(file.getOriginalFilename());
        boolean result = FileService.upload(file, path, false, null);
        if (result)
            System.out.println("success");
        else
            System.out.println("fail");
        return result;
    }

    @PostMapping("/modify")
    public boolean handleFileModify(@RequestParam("file") MultipartFile file, @RequestParam("path") String path, @RequestParam("mark") String mark) {
        System.out.println("modify request");
        System.out.println(path);
        System.out.println(file.getOriginalFilename());
        System.out.println(mark);
        boolean result = FileService.upload(file, path, true, mark);
        if (result)
            System.out.println("success");
        else
            System.out.println("fail");
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean deleteFile(String filename) {
        System.out.println("delete request");
        System.out.println(filename);
        return FileService.fileDelete(filename);
    }
}
