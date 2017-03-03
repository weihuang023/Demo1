/*
 * Decompiled with CFR 0_118.
 */
package com.RTTS.NeoLoad;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileParser {
    public static void main(String[] args) {
        String fileName = "";
        String currLine = "";
        int curlineNumber = 1;
        int outputCounter = 0;
        int totalLines = 0;
        int percentile = 0;
        int numCols = 0;
        String tempConsoleInput = "";
        String delim = "";
        String outPutFile = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            File directory;
            block19 : {
                do {
                    System.out.print("Please enter the full path and file name you wish to parse (example: c:\\testfile.csv): ");
                    fileName = br.readLine();
                    System.out.println("You entered: " + fileName);
                    if (Files.exists(Paths.get(fileName, new String[0]), new LinkOption[0])) break;
                    System.out.println("FILE NOT FOUND.");
                } while (true);
                System.out.print("Please enter the full path and file name where you would like to output the results(example: c:\\testfile.csv): ");
                outPutFile = br.readLine();
                System.out.println("You entered: " + outPutFile);
                while (Files.exists(Paths.get(outPutFile, new String[0]), new LinkOption[0])) {
                    System.out.print("A file with the same name currently exists, would you like to overwrite it? Y/N ");
                    tempConsoleInput = br.readLine();
                    if (!tempConsoleInput.trim().equalsIgnoreCase("Y")) {
                        if (tempConsoleInput.trim().equalsIgnoreCase("N")) {
                            System.out.print("Not overwriting file. Please enter a new full file path and name: ");
                            outPutFile = br.readLine();
                            System.out.println("You entered: " + outPutFile);
                            continue;
                        }
                        System.out.println("Unrecoginized input");
                        continue;
                    }
                    break block19;
                }
                System.out.println("File Does Not Exist Created File...");
            }
            File fullFilePath = new File(outPutFile);
            if (Files.exists(Paths.get(outPutFile, new String[0]), new LinkOption[0])) {
                System.out.println("Overwriting file...");
                fullFilePath.delete();
            }
            if (!(directory = new File(outPutFile.substring(0, outPutFile.lastIndexOf("\\")))).exists()) {
                System.out.println("Creating Directory...");
                directory.mkdirs();
            }
            System.out.println("Creating File " + outPutFile.substring(outPutFile.lastIndexOf("\\") + 1, outPutFile.length()));
            File file = new File(outPutFile);
            file.createNewFile();
            Path readFile = Paths.get(fileName, new String[0]);
            Charset charset = Charset.forName("UTF-8");
            BufferedReader fr = Files.newBufferedReader(readFile, charset);
            System.out.print("Please Enter Delimter Character: ");
            delim = br.readLine();
            System.out.print("Please enter the expected number of colums: ");
            numCols = Integer.parseInt(br.readLine());
            totalLines = FileParser.lineCount(fileName);
            System.out.println("Line Count: " + totalLines);
            System.out.print("Would you like to continue parsing this file? (Y/N): ");
            percentile = (int)((double)totalLines * 0.01);
            if (percentile <= 0) {
                percentile = 1;
            }
            File file1 = new File(outPutFile);
            FileWriter outFile = new FileWriter(file1.getAbsoluteFile());
            BufferedWriter bwoutFile = new BufferedWriter(outFile);
            String temp = outPutFile.substring(outPutFile.lastIndexOf("\\") + 1, outPutFile.length());
            File file2 = new File(outPutFile.replace(temp, "ERROR" + temp));
            if (file2.exists()) {
                fullFilePath.delete();
            }
            file2.createNewFile();
            FileWriter errorFile = new FileWriter(file2.getAbsoluteFile());
            BufferedWriter bwerrorFile = new BufferedWriter(errorFile);
            if (br.readLine().equalsIgnoreCase("Y")) {
                while ((currLine = fr.readLine()) != null) {
                    String[] tempTextSplit = currLine.split(delim, -1);
                    if (tempTextSplit.length != numCols) {
                        bwerrorFile.write(currLine);
                        bwerrorFile.newLine();
                    } else {
                        bwoutFile.write(currLine.replace("\"", "").trim());
                        bwoutFile.newLine();
                    }
                    if (curlineNumber % percentile == 0) {
                        int i = ++outputCounter % 10;
                        while (i > 0) {
                            System.out.print(".");
                            --i;
                        }
                        if (outputCounter >= 10) {
                            outputCounter = 0;
                            System.out.println(String.valueOf((int)((double)curlineNumber / (double)totalLines * 100.0) + 1) + "% Complete");
                        } else {
                            System.out.println("");
                        }
                    }
                    ++curlineNumber;
                }
            } else {
                System.out.println("Canceling...");
            }
            System.out.println("Completed...");
            br.close();
            fr.close();
            bwerrorFile.close();
            bwoutFile.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int lineCount(String fileName) throws IOException {
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(fileName));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                int i = 0;
                while (i < readChars) {
                    if (c[i] == 10) {
                        ++count;
                    }
                    ++i;
                }
            }
            int n = count == 0 && !empty ? 1 : count;
            return n;
        }
        finally {
            is.close();
        }
    }
}

