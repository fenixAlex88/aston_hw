package by.alex.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyLinkedListTest {
    private static final int INITIAL_CAPACITY = 10;
    private static final int NEGATIVE_INDEX = -1;
    private static final int VALUE_ZERO = 0;
    private static final int VALUE_ONE = 1;
    private static final int VALUE_TWO = 2;
    private static final int VALUE_THREE = 3;


    private MyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
    }

    @DisplayName("Adding an element to the end of the list")
    @Test
    void testAddToEnd() {
        list.add(VALUE_ONE);
        assertEquals(VALUE_ONE, list.size(), "Size should be 1 after adding one element");
        assertEquals(VALUE_ONE, list.get(0), "First element should be the added value");
    }

    @DisplayName("Adding an element at a specific index")
    @Test
    void testAddAtIndex() {
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        list.add(VALUE_ZERO, 0);
        assertEquals(VALUE_THREE, list.size(), "Size should be 3 after adding three elements");
        assertEquals(VALUE_ZERO, list.get(0), "First element should be 0");
        assertEquals(VALUE_ONE, list.get(1), "Second element should be 1");
        assertEquals(VALUE_TWO, list.get(2), "Third element should be 2");
    }

    @DisplayName("Adding an element at an invalid index throws exception")
    @Test
    void testAddAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.add(VALUE_ONE, NEGATIVE_INDEX),
                "Adding at negative index should throw IndexOutOfBoundsException");
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.add(VALUE_ONE, VALUE_ONE),
                "Adding at index beyond size should throw IndexOutOfBoundsException");
    }

    @DisplayName("Retrieving elements by index")
    @Test
    void testGetElement() {
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        assertEquals(VALUE_ONE, list.get(0), "First element should be 1");
        assertEquals(VALUE_TWO, list.get(1), "Second element should be 2");
    }

    @DisplayName("Getting an element at an invalid index throws exception")
    @Test
    void testGetOutOfBounds() {
        list.add(VALUE_ONE);
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(NEGATIVE_INDEX),
                "Getting at negative index should throw IndexOutOfBoundsException");
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(VALUE_ONE),
                "Getting at index beyond size should throw IndexOutOfBoundsException");
    }

    @DisplayName("Removing the first occurrence of an element")
    @Test
    void testRemoveElement() {
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        list.add(VALUE_ONE);
        list.remove(VALUE_ONE);
        assertEquals(VALUE_TWO, list.size(), "Size should be 2 after removing one element");
        assertEquals(VALUE_TWO, list.get(0), "First element should be 2");
        assertEquals(VALUE_ONE, list.get(1), "Second element should be 1");
    }

    @DisplayName("Removing a non-existent element")
    @Test
    void testRemoveNonExistentElement() {
        list.add(VALUE_ONE);
        list.remove(VALUE_TWO);
        assertEquals(VALUE_ONE, list.size(), "Size should remain 1 when removing non-existent element");
        assertEquals(VALUE_ONE, list.get(0), "First element should still be 1");
    }

    @DisplayName("Clearing the list of all elements")
    @Test
    void testClearList() {
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        list.clear();
        assertEquals(VALUE_ZERO, list.size(), "Size should be 0 after clearing");
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0),
                "Getting element from cleared list should throw IndexOutOfBoundsException");
    }

    @DisplayName("Sorting a list of multiple elements")
    @Test
    void testSortMultipleElements() {
        list.add(VALUE_THREE);
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        list.sort();
        assertEquals(VALUE_THREE, list.size(), "Size should remain 3 after sorting");
        assertEquals(VALUE_ONE, list.get(0), "First element should be 1");
        assertEquals(VALUE_TWO, list.get(1), "Second element should be 2");
        assertEquals(VALUE_THREE, list.get(2), "Third element should be 3");
    }

    @DisplayName("Sorting an empty list or a list with a single element")
    @Test
    void testSortEmptyOrSingleElement() {
        list.sort();
        assertEquals(VALUE_ZERO, list.size(), "Size should remain 0 for empty list");

        list.add(VALUE_ONE);
        list.sort();
        assertEquals(VALUE_ONE, list.size(), "Size should remain 1 for single-element list");
        assertEquals(VALUE_ONE, list.get(0), "Single element should remain unchanged");
    }

    @DisplayName("Sorting a list with non-comparable elements throws exception")
    @Test
    void testSortNonComparableElements() {
        MyLinkedList<Object> objList = new MyLinkedList<>();
        objList.add(new Object());
        assertThrows(UnsupportedOperationException.class,
                objList::sort,
                "Sorting non-comparable elements should throw UnsupportedOperationException");
    }

    @DisplayName("Checking size with various list states")
    @Test
    void testSizeMethod() {
        assertEquals(VALUE_ZERO, list.size(), "Initial size should be 0");
        list.add(VALUE_ONE);
        assertEquals(VALUE_ONE, list.size(), "Size should be 1 after adding one element");
        list.add(VALUE_TWO);
        assertEquals(VALUE_TWO, list.size(), "Size should be 2 after adding two elements");
    }

    @DisplayName("Testing automatic capacity expansion beyond initial capacity")
    @Test
    void testCapacityExpansion() {
        MyLinkedList<Integer> smallList = new MyLinkedList<>();
        for (int i = 0; i < (INITIAL_CAPACITY + 1); i++) {
            smallList.add(i);
        }
        assertEquals((INITIAL_CAPACITY + 1), smallList.size(), "Size should be 11 after adding 11 elements");
        for (int i = 0; i < (INITIAL_CAPACITY + 1); i++) {
            assertEquals(i, smallList.get(i), "Element at index " + i + " should be " + i);
        }
    }
}
