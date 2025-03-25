package by.alex.list;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void remove(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], value)) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                array[--size] = null;
                return;
            }
        }
    }

    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        Arrays.sort(array, 0, size, (a, b) -> ((Comparable<T>) a).compareTo((T) b));
    }

    public int size() {
        return size;
    }
}
