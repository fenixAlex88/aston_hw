package by.alex.list;

/**
 * Generic interface for a list data structure.
 * @param <T> the type of elements in this list
 */
public interface List<T> {
    void add(T value);

    void add(T value, int index);

    T get(int index);

    void remove(T value);

    void clear();

    void sort();
}
