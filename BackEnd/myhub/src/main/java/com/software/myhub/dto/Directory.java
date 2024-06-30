package com.software.myhub.dto;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Directory {
    private String name;    //文件夹（仓库也被视作文件夹）名称
    private String directory; //文件夹在服务端的目录（不展示给用户）
    private ArrayList<Directory> children;  //文件夹中的子文件夹列表
    private ArrayList<String> files;  //文件夹第一层目录中的文件在prefix_path下的相对路径，并用"->"替换"\\"，以避免传递url时出错
    public final static String prefix_path = "C:\\lessons\\2024spring\\software\\project\\new\\file\\";

    public Directory() {
        this.children = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public Directory(File directory) {
        this.name = directory.getName();
        this.directory = directory.getPath().substring(prefix_path.length()).replace("\\", "->");
        this.children = new ArrayList<>();
        this.files = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                this.children.add(new Directory(file));
            }
            else{
                if(file.getPath().startsWith(prefix_path)) {
                    this.files.add(file.getPath().substring(prefix_path.length()).replace("\\", "->"));
                }
            }
        }
    }

    public String getDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Directory> getChildren() {
        return children;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setChildren(ArrayList<Directory> children) {
        this.children = children;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }
}
