package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            for (String str = reader.readLine(); str != null; str = reader.readLine()) {
                str = str.trim();
                int ind = -1;
                if (!str.startsWith("#")) {
                    ind = str.indexOf("=");
                }
                if (ind > 0 && ind < (str.length() - 1)) {
                    values.put(str.substring(0, ind).trim(), str.substring(ind + 1).trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    return out.toString();
    }

    public static void main(String[] args) {
       Config config = new Config("./data/pair_without_comment.properties");
       System.out.println(config);
       config.load();
       config.values.forEach((key, value) -> System.out.println("ключ: " + key + ", значение: " + value));
    }

}