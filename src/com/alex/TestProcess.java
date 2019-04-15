package com.alex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by caozhennan on 2019-04-08 11:13.
 */
public class TestProcess {
    public static void main(String[] args) {
        String cmd = "/Users/didi/IdeaProjects/AlgIntro/script/sleep.sh";
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(cmd);
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            System.out.println("start");
            while ((line = r.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
