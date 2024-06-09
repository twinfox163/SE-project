package com.software.myhub.controller;

import com.software.myhub.service.DirectoryService;
import com.software.myhub.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @GetMapping("/files/{filename}")
    public ResponseEntity<Resource> showFile(@PathVariable String filename) {
        return FileService.readFile(filename);
    }

    @GetMapping("/new_dir/{path}/{dirname}")
    public String newDir(@PathVariable String path, @PathVariable String dirname) {
        if(path == null || path.isEmpty() || dirname == null || dirname.isEmpty())
            return "illegal argument";
        if (!path.contains("->"))
            return "illegal path";
        String path_decode = "C:\\lessons\\2024spring\\software\\project\\new\\file\\" + path.replace("->", "\\");
        if(DirectoryService.create(path_decode, dirname, false))
            return "success";
        else
            return "error";
    }

    @PostMapping("/upload")
    public boolean handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        return FileService.upload(file, path, false);
    }

    @PostMapping("/modify")
    public boolean handleFileModify(@RequestParam("file") MultipartFile file, @RequestParam("path") String path) {
        return FileService.upload(file, path, true);
    }
}
