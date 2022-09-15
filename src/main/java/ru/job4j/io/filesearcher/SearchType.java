package ru.job4j.io.filesearcher;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchType {
    public static Predicate<Path> getCondition(String fileName, String searchType) {
        Predicate<Path> condition = null;
        if ("name".equals(searchType)) {
            condition = file -> fileName.equals(file.toFile().getName());
        } else if ("mask".equals(searchType)) {
            condition = file -> Pattern.matches(maskToRegex(fileName), file.toFile().getName());
        } else if ("regex".equals(searchType)) {
            condition = file -> Pattern.matches(fileName, file.toFile().getName());
        }
        return condition;
    }

    private static String maskToRegex(String fileMask) {
        StringBuilder builder = new StringBuilder();
        for (char ch : fileMask.toCharArray()) {
            String stringRegex = switch (ch) {
                case '.' -> "\\.";
                case '?' -> ".";
                case '*' -> ".*";
                default -> Character.toString(ch);
            };
            builder.append(stringRegex);
        }
        return builder.toString();
    }
}
