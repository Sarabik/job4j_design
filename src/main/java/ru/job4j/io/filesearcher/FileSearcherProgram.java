package ru.job4j.io.filesearcher;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class FileSearcherProgram {
    public static void main(String[] args) throws IOException {
        ArgsValidation argsValidation = ArgsValidation.readArgsToMap(args);
        String directory = argsValidation.getArgValue("d");
        String fileName = argsValidation.getArgValue("n");
        String searchType = argsValidation.getArgValue("t");
        String outputFile = argsValidation.getArgValue("o");

        System.out.println(directory);
        System.out.println(fileName);
        System.out.println(searchType);
        System.out.println(outputFile);

        Predicate<Path> condition = SearchType.getCondition(fileName, searchType);
        List<Path> pathList = Search.search(Path.of(directory), condition);

        pathList.forEach(System.out::println);

        try (PrintWriter outWriter = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(outputFile)
                ))) {
            pathList.forEach(outWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
