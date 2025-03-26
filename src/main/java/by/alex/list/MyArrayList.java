package by.alex.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * A generic dynamic array-based implementation of the {@link List} interface.
 * This class provides a resizable array that can store elements of any type.
 *
 * @param <T> the type of elements held in this list
 */
public class MyArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param capacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified capacity is negative
     */
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + capacity);
        }
        elements = new Object[capacity];
        size = 0;
    }

    /**
     * Constructs a list containing the elements of the specified collection,
     * in the order they are returned by the collection's iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public MyArrayList(Collection<? extends T> c) {
        Objects.requireNonNull(c, "Collection must not be null");
        size = c.size();
        elements = c.toArray();
    }

    /**
     * Constructs a list containing the elements of the specified array,
     * in the same order as they appear in the array.
     *
     * @param array the array whose elements are to be placed into this list
     * @throws NullPointerException if the specified array is null
     */
    public MyArrayList(T[] array) {
        Objects.requireNonNull(array, "Array must not be null");
        size = array.length;
        elements = Arrays.copyOf(array, size);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param value the element to be appended to this list
     */
    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     *
     * @param value the element to be inserted
     * @param index the index at which the element should be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    @Override
    public void add(T value, int index) {
        validateIndex(index, true);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        validateIndex(index, false);
        return (T) elements[index];
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present. If the list does not contain the element, it remains unchanged.
     *
     * @param value the element to be removed from this list, if present
     */
    @Override
    public void remove(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], value)) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;
                return;
            }
        }
    }

    /**
     * Removes all elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * Sorts this list according to the natural ordering of its elements.
     * All elements in the list must implement the {@link Comparable} interface.
     *
     * @throws UnsupportedOperationException if the elements do not implement {@link Comparable}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        if (size > 0 && !(elements[0] instanceof Comparable)) {
            throw new UnsupportedOperationException("Elements must implement Comparable for sorting");
        }
        if (size <= 1) {
            return;
        }
        Arrays.sort(elements, 0, size, (a, b) -> ((Comparable<T>) a).compareTo((T) b));
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Ensures that this list has enough capacity to hold additional elements.
     * If the current size equals the array length, the array is doubled in size.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    /**
     * Validates that the specified index is within the acceptable range for this list.
     *
     * @param index    the index to validate
     * @param allowEnd if true, allows the index to equal the size (for insertion);
     *                 if false, requires the index to be less than the size (for access)
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || (allowEnd ? index > size : index >= size))
     */
    private void validateIndex(int index, boolean allowEnd) {
        boolean isInvalid = index < 0 || (allowEnd ? index > size : index >= size);
        if (isInvalid) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}