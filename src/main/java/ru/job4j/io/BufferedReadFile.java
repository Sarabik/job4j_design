package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReadFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            StringBuilder text = new StringBuilder();
            in.lines().forEach(System.out::println);
            /*альтернативная запись через цикл
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println(line);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
