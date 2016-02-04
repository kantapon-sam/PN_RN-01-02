package com.java.myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class PN_RN_01 {

    public static void main(String[] args) {

        Dialog.setLAF();
        Selectfile Select = new Selectfile();
        int index = -1;
        File[] files = Select.getFile().listFiles();
        Wait wait = new Wait();
        try {
            if (Select.getChooser().getSelectedFile().getName().contains(".csv")) {
                BufferedReader br = new BufferedReader(new FileReader(Select.getFile()));
                BufferedReader br_interface = new BufferedReader(new FileReader(Select.getFile()));
                String pathOutput = Select.getFile().getName().split(".txt")[0];
                new File(Select.getChooser().getCurrentDirectory() + "\\" + pathOutput.concat("a.csv")).delete();

                Sub(br, br_interface, pathOutput, Select.getChooser().getCurrentDirectory());
                wait.dispose();
                Dialog.Success();
                System.exit(0);
            } else {
                Arrays.sort(files, new Comparator<File>() {
                    public int compare(File f1, File f2) {
                        return Long.compare(f1.lastModified(), f2.lastModified());
                    }
                });
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().contains(".csv")) {
                        files[i].delete();
                    }
                }
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().contains(".txt")) {
                        index = i;
                        BufferedReader br = new BufferedReader(new FileReader(files[i]));
                        BufferedReader br_interface = new BufferedReader(new FileReader(files[i]));
                        String pathOutput = files[i].getName().split(".txt")[0];
                        Sub(br, br_interface, pathOutput, Select.getChooser().getSelectedFile());

                    }
                }
                wait.dispose();
                Dialog.Success();
                System.exit(0);
            }
        } catch (NullPointerException ex) {
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void Sub(BufferedReader br, BufferedReader br_interface, String path, File Directory) throws IOException {
        String line;
        String str1 = "";
        String str2 = "";
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(" ");
            if (line.contains("Yes")) {
                str1 += arr[0] + "\n";
            }
        }
        br.close();

        while ((line = br_interface.readLine()) != null) {
            if ((line.contains("interface") || line.contains("description") || line.contains("service-policy output")) && (!line.contains("#show"))) {
                if (line.contains("interface")) {
                    str2 += "\n";
                }
                str2 += line + ",";
            }
        }
        br_interface.close();
        FileWriter A;
        A = new FileWriter(Directory + "\\" + path + "a" + ".csv", true);
        Writer.A(A, str1, str2);

    }
}
