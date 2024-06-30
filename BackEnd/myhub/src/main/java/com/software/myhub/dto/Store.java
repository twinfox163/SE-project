package com.software.myhub.dto;

import java.io.*;
import java.util.ArrayList;

public class Store extends Directory {
    private ArrayList<History> history;
    private ArrayList<String> usersFollow;
    //@JsonIgnore
    private File logFile;
    //@JsonIgnore
    private File userFile;

    public Store() {
        super();
        history = new ArrayList<>();
        usersFollow = new ArrayList<>();
    }

    public Store(File file) {
        super(file);
        history = new ArrayList<>();
        logFile = new File(file.getAbsolutePath() + ".log");
        userFile = new File(file.getAbsolutePath() + ".users");
        usersFollow = new ArrayList<>();
        BufferedReader br;
        if (logFile.exists()) {
            try {
                br = new BufferedReader(new FileReader(logFile));
                String line;
                while ((line = br.readLine()) != null)
                {
                    history.add(new History(line));
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (userFile.exists()) {
            try {
                br = new BufferedReader(new FileReader(userFile));
                String line;
                while ((line = br.readLine()) != null)
                {
                    usersFollow.add(line);
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public File getLogFile() {
        return logFile;
    }

    public File getUserFile() {
        return userFile;
    }

    public ArrayList<String> getUsersFollow() {
        return usersFollow;
    }

    public boolean tobeFollowed(String username) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(userFile, true))){
            pw.println(username);
            pw.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
