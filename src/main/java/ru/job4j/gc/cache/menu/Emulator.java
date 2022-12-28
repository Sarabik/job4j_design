package ru.job4j.gc.cache.menu;


import ru.job4j.gc.cache.AbstractCache;
import ru.job4j.gc.cache.DirFileCache;

import java.io.File;
import java.util.Scanner;

public class Emulator {

    public static final String MENU_START = "Укажите путь к директории с файлом (-ами) для загрузки или q для выхода";
    public static final String DIRECTORY_PATH = "Текущая директория для загрузки файлов: ";
    public static final String MENU_DIR = """
                Введите 1, чтобы указать имя файла и загрузить его содержимое.
                Введите 2, чтобы выбрать другую директорию.
                Введите любое другое число для выхода.
            """;
    public static final String FILE_ASK = "Укажите название файла";
    public static final String FILE_READ = "Содержание файла:";
    public static final int CHOOSE_FILE = 1;
    public static final int CHANGE_DIRECTORY = 2;

    void menu(Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.println(MENU_START);
            String directory = scanner.nextLine();
            if ("q".equals(directory)) {
                run = false;
            } else if (!(new File(directory).isDirectory())) {
                System.out.println("Ошибка: указанная директория не существует");
                continue;
            }
            AbstractCache<String, String> cache = new DirFileCache(directory);
            while (run) {
                System.out.println(System.lineSeparator() + DIRECTORY_PATH);
                System.out.println(directory);
                System.out.println(MENU_DIR);
                int userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice == CHOOSE_FILE) {
                    System.out.println(FILE_ASK);
                    String fileName = scanner.nextLine();
                    if (!(new File(directory + "\\" + fileName).isFile())) {
                        System.out.println("Ошибка: указанный файл не существует");
                        continue;
                    }
                    System.out.println(FILE_READ);
                    System.out.println(cache.get(fileName));
                } else if (userChoice == CHANGE_DIRECTORY) {
                    break;
                } else {
                    run = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator();
        emulator.menu(scanner);
    }
}
