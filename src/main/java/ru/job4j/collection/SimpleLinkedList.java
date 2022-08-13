package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private Node<E> head;
    private int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> last = head;
        Node<E> newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private Node<E> itElement = head;
            private boolean firstIteration;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size != 0 && itElement.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (itElement == head && !firstIteration) {
                    firstIteration = true;
                } else {
                    itElement = itElement.next;
                }
                return itElement.item;
            }
        };
    }
}