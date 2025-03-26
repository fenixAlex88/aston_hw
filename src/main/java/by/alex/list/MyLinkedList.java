package by.alex.list;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A generic doubly-linked list implementation of the {@link List} and {@link Iterable} interfaces.
 * Provides efficient insertion, removal, and iteration operations.
 *
 * @param <E> the type of elements held in this list
 */
public class MyLinkedList<E> implements List<E>, Iterable<E> {

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

    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList() {
        size = 0;
    }

    /**
     * Constructs a linked list containing the elements of the specified collection,
     * in the order they are returned by the collection's iterator.
     *
     * @param collection the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public MyLinkedList(Collection<? extends E> collection) {
        this();
        Objects.requireNonNull(collection, "Collection must not be null");
        addAll(collection);
    }

    /**
     * Constructs a linked list containing the elements of the specified array,
     * in the same order as they appear in the array.
     *
     * @param array the array whose elements are to be placed into this list
     * @throws NullPointerException if the specified array is null
     */
    public MyLinkedList(E[] array) {
        this();
        Objects.requireNonNull(array, "Array must not be null");
        addAll(Arrays.asList(array));
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param value element to be appended to this list
     */
    @Override
    public void add(E value) {
        linkNodeAsLast(new Node(value));
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param value element to be inserted
     * @param index index at which the specified element is to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    @Override
    public void add(E value, int index) {
        validateIndexForAdd(index);
        if (index == size) {
            add(value);
            return;
        }
        Node newNode = new Node(value);
        if (index == 0) {
            linkNodeAsFirst(newNode);
        } else {
            getNode(index).ifPresent(current -> linkNodeBefore(newNode, current));
        }
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) {
        return getNode(index)
                .map(node -> node.data)
                .orElseThrow(() -> new IndexOutOfBoundsException("Index: " + index + ", Size: " + size));
    }

    /**
     * Retrieves the node at the specified index.
     *
     * @param index index of the node to retrieve
     * @return an {@link Optional} containing the node, or empty if the index is invalid
     */
    private Optional<Node> getNode(int index) {
        if (index < 0 || index >= size) {
            return Optional.empty();
        }
        return Optional.of(traverseToNode(index));
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param value element to be removed from this list, if present
     */
    @Override
    public void remove(E value) {
        Stream.iterate(head, Objects::nonNull, node -> node.next)
                .filter(node -> Objects.equals(node.data, value))
                .findFirst()
                .ifPresent(this::removeNode);
    }

    /**
     * Removes all elements from this list.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Sorts this list according to the natural ordering of its elements.
     *
     * @throws UnsupportedOperationException if the elements do not implement {@link Comparable}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void sort() {
        if (size <= 1 || head == null) return;
        if (!(head.data instanceof Comparable)) {
            throw new UnsupportedOperationException("Elements must implement Comparable for sorting");
        }
        E[] array = toArray();
        Arrays.sort(array, (a, b) -> ((Comparable<E>) a).compareTo(b));
        updateListFromArray(array);
    }

    /**
     * Converts this list to an array.
     *
     * @return an array containing all elements in this list in proper sequence
     */
    @SuppressWarnings("unchecked")
    private E[] toArray() {
        return (E[]) Stream.iterate(head, Objects::nonNull, node -> node.next)
                .map(node -> node.data)
                .toArray();
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
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    @NotNull
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    // Private helper methods

    /**
     * Validates the index for insertion operations.
     *
     * @param index the index to validate
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Traverses the list to find the node at the specified index.
     *
     * @param index the index of the node to find
     * @return the node at the specified index
     */
    private Node traverseToNode(int index) {
        Node current = (index < size / 2) ? head : tail;
        int steps = (index < size / 2) ? index : size - 1 - index;
        for (int i = 0; i < steps; i++) {
            current = (index < size / 2) ? current.next : current.prev;
        }
        return current;
    }

    /**
     * Links the new node as the first element in the list.
     *
     * @param newNode the node to link
     */
    private void linkNodeAsFirst(Node newNode) {
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
    }

    /**
     * Links the new node as the last element in the list.
     *
     * @param newNode the node to link
     */
    private void linkNodeAsLast(Node newNode) {
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /**
     * Links the new node before the specified existing node.
     *
     * @param newNode the node to insert
     * @param current the node before which to insert
     */
    private void linkNodeBefore(Node newNode, Node current) {
        newNode.prev = current.prev;
        newNode.next = current;
        if (current.prev != null) {
            current.prev.next = newNode;
        }
        current.prev = newNode;
    }

    /**
     * Unlinks the specified node from the list and adjusts size.
     *
     * @param node the node to unlink
     */
    private void removeNode(Node node) {
        unlinkNode(node);
        size--;
    }

    /**
     * Unlinks the specified node from the list without adjusting size.
     *
     * @param node the node to unlink
     */
    private void unlinkNode(Node node) {
        if (node == head) {
            head = node.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (node == tail) {
            tail = node.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    /**
     * Adds all elements from the specified collection to this list.
     *
     * @param collection the collection whose elements are to be added
     */
    private void addAll(Collection<? extends E> collection) {
        collection.forEach(this::add);
    }

    /**
     * Updates the list's data from the specified array.
     *
     * @param array the array containing the new data
     */
    private void updateListFromArray(E[] array) {
        Node current = head;
        for (E value : array) {
            current.data = value;
            current = current.next;
        }
    }

    /**
     * An iterator over the elements of this linked list.
     */
    private class LinkedListIterator implements Iterator<E> {
        private Node current = head;

        /**
         * Returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if there are more elements to iterate over
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws java.util.NoSuchElementException if there are no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements in the list");
            }
            E data = current.data;
            current = current.next;
            return data;
        }
    }
}