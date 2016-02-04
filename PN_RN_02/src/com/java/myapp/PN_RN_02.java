package com.java.myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PN_RN_02 {

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
                new File(Select.getChooser().getCurrentDirectory() + "\\summary.csv").delete();

                Sub(br, br_interface, pathOutput, Select.getChooser().getCurrentDirectory());
                wait.dispose();
                Dialog.Success();
                System.exit(0);
            } else {
                new File(Select.getChooser().getSelectedFile() + "\\summary.csv").delete();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().contains("a.csv")) {
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
        int a = 0;
        int b = 0;
        int t = 0;
        String[] s0 = new String[10000];
        String[] s1 = new String[10000];
        String[] sum = new String[10000];
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if (!arr[0].contains("interface") && arr[0].length() > 1) {

                s0[a] = "interface ".concat(arr[0]);
                a++;
            }
        }
        br.close();

        while ((line = br_interface.readLine()) != null) {

            if (line.contains("interface")) {
                s1[b] = line;
                b++;
            }
        }

        br_interface.close();

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (s0[i].equals(s1[j].split(",")[0])) {
                    sum[i] = s1[j];
                    t++;
                }
            }
        }
        for (int i = 0; i < t; i++) {
            String[] arr = sum[i].split(",");
            switch (arr.length) {
                case 1:
                    str1 = path.split("a.csv")[0] + "," + arr[0].split("interface ")[1] + ",-,-";
                    break;
                case 2:
                    str1 += path.split("a.csv")[0] + "," + arr[0].split("interface ")[1] + "," + arr[1].split("description ")[1] + ",-" + "\n";
                    break;
                case 3:
                    str1 += path.split("a.csv")[0] + "," + arr[0].split("interface ")[1] + "," + arr[1].split("description ")[1] + "," + arr[2].split(" service-policy output ")[1] + "\n";
                    break;
                default:
                    break;
            }

        }
        System.out.println(str1);
        FileWriter summary;
        summary = new FileWriter(Directory + "\\summary.csv", true);
        Writer.A(summary, str1);
    }
}
