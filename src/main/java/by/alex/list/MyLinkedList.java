package by.alex.list;

import java.util.Arrays;

public class MyLinkedList<E> implements List<E> {

    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        size = 0;
    }

    @Override
    public void add(E value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    @Override
    public void add(E value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
        } else if (index == size) {
            add(value);
            return;
        } else {
            Node current = getNode(index);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void remove(E value) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    }
                }
                else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                }
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }

    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        if (size > 0 && !(head instanceof Comparable)) {
            throw new UnsupportedOperationException("Elements must implement Comparable for sorting");
        }
        if (size <= 1) return;
        Object[] array = toArray();
        Arrays.sort(array, (a, b) -> ((Comparable<E>) a).compareTo((E) b));
        Node current = head;
        for (Object value : array) {
            current.data = (E) value;
            current = current.next;
        }
    }

    private Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public int size() {
        return size;
    }
}
