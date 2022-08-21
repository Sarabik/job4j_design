package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            capacity *= 2;
            expand();
        }
        boolean result = false;
        int index = indexFor(hash(key));
        if (table[index] == null) {
            table[index] = new MapEntry(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() ^ (key.hashCode() >>> capacity);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] oldMap = table;
        table = new MapEntry[capacity];
        modCount = 0;
        count = 0;
        for (MapEntry<K, V> entry : oldMap) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
        oldMap = null;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(key));
        K otherKey = table[index] != null ? table[index].key : null;
        if (hash(key) == hash(otherKey) && Objects.equals(key, otherKey)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key));
        K otherKey = table[index] != null ? table[index].key : null;
        if (hash(key) == hash(otherKey) && Objects.equals(key, otherKey)) {
            table[index] = null;
            result = true;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int cursor = 0;
            private int found = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return found < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[cursor] == null) {
                    cursor++;
                }
                found++;
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}