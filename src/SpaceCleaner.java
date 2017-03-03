import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpaceCleaner
{
    public static void main(final String[] array) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        boolean b = false;
        System.out.println("Starting Cleaner...");
        System.out.print("Please enter the folder name which contains the files to remove spaces: ");
        try {
            line = bufferedReader.readLine();
        }
        catch (Exception ex) {
            System.out.println(ex.getStackTrace());
            b = true;
        }
        final File file;
        if (!(file = new File(line)).exists() && !file.isDirectory()) {
            b = true;
            System.out.println("Item does not exist, or is not a folder");
        }
        if (!b) {
            final ArrayList list = new ArrayList<File>(Arrays.asList(file.listFiles()));
            final ArrayList list2 = new ArrayList<String>(Arrays.asList(file.list()));
            System.out.println("The following Files Were Found");
            if (a(list2).matches("[yY]")) {
                if (a(line)) {
                    for (int i = 0; i < list.size(); ++i) {
//                        final File a;
//                        if ((a = a(list.get(i), list2.get(i), line)) != null) {
                            System.out.println("Parsing File: " + list2.get(i));
//                            a(list.get(i), a);
//                        }
                    }
                    return;
                }
                System.out.println("An Error has occured when creating the folder");
            }
        }
    }
    
    private static String a(final ArrayList list) {
        String line = "N";
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        if (list.size() <= 0) {
            System.out.println("No Files found");
            return line;
        }
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
        System.out.print("Would you like to continue Y/N?: ");
        try {
            line = bufferedReader.readLine();
        }
        catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return line;
    }
    
    private static boolean a(final String s) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final File file;
        if ((file = new File(String.valueOf(s) + "\\output")).exists()) {
            System.out.print("Folder Already Exists. Would you like to overwrite the folder? Y/N: ");
            try {
                if (bufferedReader.readLine().matches("[yY]")) {
                    a(file);
                    System.out.println("Creating Folder");
                    return file.mkdirs();
                }
                return false;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        System.out.println("Creating Folder");
        return file.mkdirs();
    }
    
    private static File a(File file, final String s, final String s2) {
        if (file.isDirectory()) {
            System.out.println("Skipping item: " + s + " This item is a folder");
            return null;
        }
        file = new File(String.valueOf(s2) + "\\output\\" + s);
        try {
            file.createNewFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return file;
    }
    
    private static boolean a(final File file) {
        final File[] listFiles;
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (int i = 0; i < listFiles.length; ++i) {
                if (listFiles[i].isDirectory()) {
                    a(listFiles[i]);
                }
                else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }
    
    private static void a(final File file, final File file2) {
        try {
            final LineNumberReader lineNumberReader;
            (lineNumberReader = new LineNumberReader(new FileReader(file))).skip(Long.MAX_VALUE);
            System.out.println("Line Count: " + lineNumberReader.getLineNumber());
            lineNumberReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            final FileWriter fileWriter = new FileWriter(file2);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileWriter.write(String.valueOf(line.replaceAll("\\s+", "")) + "\r\n");
            }
            fileWriter.close();
            bufferedReader.close();
            System.out.println("File cleaning complete.");
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
}
