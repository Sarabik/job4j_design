package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class CSVSearch {

    public static void validation(String[] args) {
        Path path = Path.of(args[0]);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Source file does not exist");
        }
        if (!args[0].endsWith(".csv")) {
            throw new IllegalArgumentException("Source file extension is not .csv");
        }
        if (!"stdout".equals(args[1]) && !args[1].endsWith(".csv")) {
            throw new IllegalArgumentException("Result file is not equal to \"stdout\" or file extension is not .csv");
        }
    }
}
