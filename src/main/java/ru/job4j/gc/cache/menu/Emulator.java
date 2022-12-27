package ru.job4j.gc.cache.menu;


import ru.job4j.gc.cache.AbstractCache;
import ru.job4j.gc.cache.DirFileCache;

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
    public static final String FILE_READ = "Содержание файла: ";

    void menu(Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.println(MENU_START);
            String directory = scanner.nextLine();
            if ("q".equals(directory)) {
                run = false;
            }
            /* проверка директории */
            AbstractCache<String, String> cache = new DirFileCache(directory);
            while (run) {
                System.out.println(DIRECTORY_PATH);
                System.out.println(directory);
                System.out.println(MENU_DIR);
                int userChoice = Integer.parseInt(scanner.nextLine());
                if (userChoice == 1) {
                    System.out.println(FILE_ASK);
                    String fileName = scanner.nextLine();
                    /* проверка файла */
                    /*String text = cache.load(fileName);
                    System.out.println(FILE_READ);
                    System.out.println(text);*/
                } else if (userChoice == 2) {
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
