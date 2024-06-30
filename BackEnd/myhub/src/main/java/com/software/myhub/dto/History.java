package com.software.myhub.dto;


//logfile中log的格式：每行一条，每条形如: username->log->time
public class History {
    private String username;
    private String log;
    private String time;
    public History(String username, String log, String time) {
        this.username = username;
        this.log = log;
        this.time = time;
    }

    public History(String logLine){
        if(logLine.split("->").length == 3){
            this.username = logLine.split("->")[0];
            this.log = logLine.split("->")[1];
            this.time = logLine.split("->")[2];
//            System.out.println(username);
//            System.out.println(log);
//            System.out.println(time);
        }
        else {
            this.username = "";
            this.log = "";
            this.time = "";
            System.out.println("Invalid log line : " + logLine);
        }
    }

    public History() {}

    public String getUsername() {
        return username;
    }

    public String getLog() {
        return log;
    }

    public String getTime() {
        return time;
    }
}
