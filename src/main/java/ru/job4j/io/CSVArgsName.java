package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class CSVArgsName {

    private final Map<String, String> argMap = new HashMap<>();

    public String get(String key) {
        String value = argMap.get(key);
        if (value == null) {
            throw new IllegalArgumentException(
                    String.format("Argument \"%s\" does not exist", key));
        }
        return value;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("There should be four arguments: path, delimiter, out, filter");
        }
        for (String str : args) {
            int ind = argValidation(str);
            argMap.put(str.substring(1, ind), str.substring(ind + 1));
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

    public static CSVArgsName of(String[] args) {
        CSVArgsName argNames = new CSVArgsName();
        argNames.parse(args);
        return argNames;
    }
}
