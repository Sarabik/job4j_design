package ru.job4j.ood.srp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class PathToFile {

    public static Path getPath() {
        Scanner scanner = new Scanner(System.in);
        String address = scanner.nextLine();
        Path path = Path.of(address);
        if (!Files.exists(path) || !path.endsWith(".json")) {
            path = null;
        }
        return path;
    }
}
