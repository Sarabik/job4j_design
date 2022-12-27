package ru.job4j.gc.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value = get(key);
        if (value == null) {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(cachingDir + "\\" + key))) {
                StringJoiner joiner = new StringJoiner(System.lineSeparator());
                reader.lines().forEach(joiner::add);
                value = joiner.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cache.put(key, new SoftReference<>(value));
        }
        System.out.println(value + System.lineSeparator());
        return value;
    }
}