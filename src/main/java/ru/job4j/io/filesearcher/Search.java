package ru.job4j.io.filesearcher;

import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search extends SimpleFileVisitor<Path> {

    private List<Path> paths = new ArrayList<>();
    private Predicate<Path> condition;

    public Search(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            paths.add(file);
        }
        return CONTINUE;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        Search searcher = new Search(condition);
        Files.walkFileTree(root, searcher);
        return searcher.paths;
    }

}
