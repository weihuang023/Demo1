/*
 * Decompiled with CFR 0_118.
 */
package com.RTTS.NeoLoad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private String AgentFile;
    private String FileLocation;
    private String AgentID;

    public void logger(String loc) {
        this.AgentFile = loc;
    }

    public void logger() {
        this.AgentFile = "C:\\";
    }

    public String vUserID(String id) throws IOException {
        File path = new File("c:\\log");
        if (!path.exists()) {
            path.mkdir();
            this.FileLocation = "c:\\log";
        } else {
            this.FileLocation = "c:\\log";
        }
        
        long currentTime = System.currentTimeMillis();
        System.out.println("Current Time" +currentTime);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-h-mm-ss-a");
        String formattedDate = sdf.format(date);
        File testFile = new File(String.valueOf(this.FileLocation) + "\\" + id + "_" + formattedDate);
        testFile.createNewFile();
        this.AgentID = id;
        System.out.println("AgentID" + this.AgentID);
        this.AgentFile = String.valueOf(this.FileLocation) + "\\" + id + "_" + formattedDate;
        return this.AgentFile;
    }

    public void log(String text) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss:a");
        String formattedDate = sdf.format(date);
        FileWriter fw = new FileWriter(this.AgentFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(String.valueOf(formattedDate) + " : " + text);
        bw.close();
    }
}

