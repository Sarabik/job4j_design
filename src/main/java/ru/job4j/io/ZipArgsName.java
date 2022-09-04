package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ZipArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException(
                    String.format("Argument \"%s\" does not exist", key));
        }
        return value;
    }

    private void parse(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("There should be three arguments");
        }
        for (String str : args) {
            int ind = argValidation(str);
            values.put(str.substring(1, ind), str.substring(ind + 1));
        }
    }

    private static int argValidation(String argument) {
        if (!argument.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("Argument \"%s\" should start with \"-\" symbol", argument));
        }
        int ind = argument.indexOf("=");
        if (ind < 2 || ind >= argument.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("Argument \"%s\" should match the required pattern \"-key=value\"", argument));
        }
        return ind;
    }

    public static ZipArgsName of(String[] args) {
        ZipArgsName names = new ZipArgsName();
        names.parse(args);
        return names;
    }
}
