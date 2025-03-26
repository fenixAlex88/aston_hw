package by.alex.list;

/**
 * A generic interface for a list data structure.
 * @param <T> the type of elements in this list
 */
public interface List<T> {
    /**
     * Appends the specified element to the end of the list.
     * @param value the element to be appended
     */
    void add(T value);

    /**
     * Inserts the specified element at the specified position in the list.
     * @param value the element to be inserted
     * @param index the index at which the element should be inserted
     */
    void add(T value, int index);

    /**
     * Returns the element at the specified position in the list.
     * @param index the index of the element to return
     * @return the element at the specified position
     */
    T get(int index);

    /**
     * Removes the first occurrence of the specified element from the list.
     * @param value the element to be removed
     */
    void remove(T value);

    /**
     * Removes all elements from the list.
     */
    void clear();

    /**
     * Sorts the list according to the natural ordering of its elements.
     */
    void sort();

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    int size();
}