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
            condition = file -> maskCheck(file.toFile().getName(), fileName);
        } else if ("regex".equals(searchType)) {
            condition = file -> Pattern.matches(fileName, file.toFile().getName());
        }
        return condition;
    }

    private static boolean maskCheck(String file, String fileMask) {
        char[] fileArray = file.toCharArray();
        char[] maskArray = fileMask.toCharArray();
        int fileCounter = 0;
        int maskCounter = 0;
        int starIndex = -1;
        boolean result = false;

        while (fileCounter < fileArray.length && maskCounter < maskArray.length) {
            if (maskArray[maskCounter] == '*') {
                starIndex = maskCounter;
                maskCounter++;
            } else if (maskArray[maskCounter] == '?' || maskArray[maskCounter] == fileArray[fileCounter]) {
                result = true;
                maskCounter++;
                fileCounter++;
            } else if (maskArray[maskCounter] != fileArray[fileCounter]) {
                if (starIndex != -1 && result) {
                    maskCounter = starIndex + 1;
                } else if (starIndex == -1) {
                    result = false;
                    break;
                } else {
                    fileCounter++;
                }
                result = false;
            }
        }
        return result;
    }
}
