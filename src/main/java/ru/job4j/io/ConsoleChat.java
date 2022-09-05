package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrasesList = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner console = new Scanner(System.in);
        String userPhrase = null;
        String botPhrase = null;
        boolean botOn = true;
        do {
            userPhrase = console.nextLine();
            log.add(userPhrase);
            switch (userPhrase) {
                case OUT:
                    break;
                case STOP:
                    botOn = false;
                    break;
                case CONTINUE:
                    botOn = true;
                default:
                    if (botOn) {
                        botPhrase = phrasesList.get((int) (Math.random() * phrasesList.size()));
                        System.out.println(botPhrase);
                        log.add(botPhrase);
                    }
            }
        }
        while (!OUT.equals(userPhrase));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrasesList = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            phrasesList = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrasesList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\data\\chatlog.txt", ".\\data\\answers.txt");
        cc.run();
    }
}