package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String startTime = null;
            for (String str = reader.readLine(); str != null; str = reader.readLine()) {
                if (str.startsWith("400") || str.startsWith("500")) {
                    if (startTime == null) {
                        startTime = str.substring(4);
                    }
                } else if (startTime != null) {
                    writer.printf("%s;%s%n", startTime, str.substring(4));
                    startTime = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy test = new Analizy();
        test.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}
