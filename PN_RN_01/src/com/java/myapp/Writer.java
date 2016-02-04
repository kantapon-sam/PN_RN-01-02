package com.java.myapp;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public static void A(FileWriter A, String str1, String str2) {
        try {
            A.write(str1+"\n\n"+str2);
            A.close();
            System.out.println("Write Success");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
