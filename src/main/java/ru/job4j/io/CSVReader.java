package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class CSVReader {

    public static void handle(CSVArgsName argsName) {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        CSVSearch.validation(new String[]{path, out});

        List<List<String>> listOfCells = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                List<String> list = new ArrayList<>();
                String line = reader.readLine();
                Scanner scanner = new Scanner(new ByteArrayInputStream(line.getBytes()))
                        .useDelimiter(delimiter);
                while (scanner.hasNext()) {
                    list.add(scanner.next());
                }
                listOfCells.add(new ArrayList<>(list));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] filterIndex = getFilterIndex(filter, listOfCells);

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(out, Charset.forName("WINDOWS-1251"), true))) {
            for (List<String> line : listOfCells) {
                StringJoiner joiner = new StringJoiner(delimiter);
                for (int index : filterIndex) {
                    joiner.add(line.get(index));
                }
                if ("stdout".equals(out)) {
                    System.out.println(joiner);
                } else {
                    writer.println(joiner);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] getFilterIndex(String filter, List<List<String>> listOfCells) {
        String[] filterArray = filter.split(",");
        int[] filterIndex = new int[filterArray.length];
        List<String> headRow = listOfCells.get(0);
        for (int i = 0; i < filterArray.length; i++) {
            filterIndex[i] = headRow.indexOf(filterArray[i]);
        }
        return filterIndex;
    }

    public static void main(String[] args) throws Exception {
        CSVArgsName csvArgsName = CSVArgsName.of(args);
        handle(csvArgsName);
    }
}
