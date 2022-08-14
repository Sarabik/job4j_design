package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in.isEmpty()) {
            throw new NoSuchElementException();
        }
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        T t = out.pop();
        while (!out.isEmpty()) {
            in.push(out.pop());
        }
        return t;
    }

    public void push(T value) {
        in.push(value);
    }

}