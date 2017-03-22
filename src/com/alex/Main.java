package com.alex;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Serializable {

    private static int I=0;
    private String word="";

    public static void setI(int i){
        Main.I=i;
    }
    public static int getI() {
        return I;
    }
    public void setWord(String word){
        this.word=word;
    }
    public String getWord() {
        return word;
    }

    public static void main(String[] args) {
//        for (int i = 3; ; i += 2) {
//            if (isValid(63 * i)) {
//                System.out.println(63 * i);
//                return;
//            }
//        }
        Main object = new Main();
        object.setWord("123");
        object.setI(5);

        ObjectOutputStream oos=null;
        ObjectInputStream ois=null;
        try {
            FileOutputStream fos = new FileOutputStream("dataObject.out");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private static boolean isValid(int n) {
        return n % 8 == 1 && n % 6 == 3 && n % 5 == 4;
    }
}
