package com.java.myapp;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    public static void B(FileWriter B, String str2) {
        try {
            B.write(str2);
            B.close();
            System.out.println("Write Success");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void A(FileWriter A, String str1) {
        try {
            A.write(str1);
            A.close();
            System.out.println("Write Success");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
