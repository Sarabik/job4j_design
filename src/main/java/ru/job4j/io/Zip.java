package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {


    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Path file : sources) {
            packSingleFile(file.toFile(), target);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ZipArgsName zipArgsName = ZipArgsName.of(args);
        String directory = zipArgsName.get("d");
        String exclude = zipArgsName.get("e");
        String output = zipArgsName.get("o");

        ZipSearch.validation(new String[]{directory, exclude});
        List<Path> pathList = ZipSearch.search(Path.of(directory),
                p -> !p.toFile().getName().endsWith(exclude));

        Zip zipFiles = new Zip();
        zipFiles.packFiles(pathList, new File(output));
    }
}
