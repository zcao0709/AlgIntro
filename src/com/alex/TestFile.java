package com.alex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by caozhennan on 2019-02-22 19:36.
 */
public class TestFile {
    public static void main(String[] args) {
        File f = new File("/Users/didi/Downloads/20080411_libiao.txt");
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            System.out.printf("\"%s\"\n", line);
            System.out.printf("\"%s\"\n", line.trim());
        }
    }
}
