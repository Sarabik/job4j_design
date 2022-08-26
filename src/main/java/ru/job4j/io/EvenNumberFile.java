package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("even.txt");
            StringBuilder str = new StringBuilder();
            int s;
            while ((s = in.read()) != -1) {
                str.append((char) s);
            }
            for (String num : str.toString().split(System.lineSeparator())) {
                System.out.println(num + " - " + (Integer.parseInt(num) % 2 == 0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
