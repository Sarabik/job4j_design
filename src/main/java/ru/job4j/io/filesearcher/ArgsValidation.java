package ru.job4j.io.filesearcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ArgsValidation {
    private final Map<String, String> argsMap = new HashMap<>();

    public static ArgsValidation readArgsToMap(String[] args) {
        ArgsValidation validator = new ArgsValidation();
        validator.parse(args);
        return validator;
    }

    private void parse(String[] args) {
        if (args.length != 4) {
            String s = System.lineSeparator();
            throw new IllegalArgumentException("There should be four arguments:" + s
            + "-d search directory" + s
            + "-n file name" + s
            + "-t filename declaration type: name, mask or regex" + s
            + "-o output file");
        }
        for (String str : args) {
            int equalIndex = argsPatternValidation(str);
            String key = str.substring(1, equalIndex);
            String value = str.substring(equalIndex + 1);
            argsContentValidation(key, value);
            argsMap.put(key, value);
        }
        if (argsMap.size() != 4) {
            throw new IllegalArgumentException("Some types of arguments are repeated");
        }
    }

    private static int argsPatternValidation(String str) {
        if (!str.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("Argument \"%s\" should start with \"-\" symbol", str));
        }
        int equalsIndex = str.indexOf("=");
        if (equalsIndex < 2 || equalsIndex >= str.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("Argument \"%s\" should match the required pattern \"-key=value\"", str));
        }
        return equalsIndex;
    }

    private static void argsContentValidation(String key, String value) {
        switch (key) {
            case "d":
                Path path = Path.of(value);
                if (!Files.exists(path) && !Files.isDirectory(path)) {
                    throw new IllegalArgumentException(String.format("Search directory \"%s\" does not exist", value));
                }
                break;
            case "n", "o":
                break;
            case "t":
                if (!("name".equals(value) || "mask".equals(value) || "regex".equals(value))) {
                    throw new IllegalArgumentException(String.format("Irrelevant filename declaration type - \"%s\". "
                            + "It should be: name, mask or regex", value));
                }
                break;
            default:
                String s = System.lineSeparator();
                throw new IllegalArgumentException(String.format("-\"%s\" - irrelevant argument type. "
                        + "It should be one of the following:" + s
                        + "-d search directory" + s
                        + "-n file name" + s
                        + "-t filename declaration type: name, mask or regex" + s
                        + "-o output file",
                        key));
        }
    }

    public String getArgValue(String key) {
        String value = argsMap.get(key);
        if (value == null) {
            throw new IllegalArgumentException(
                    String.format("Argument \"%s\" does not exist", key));
        }
        return value;
    }
}
