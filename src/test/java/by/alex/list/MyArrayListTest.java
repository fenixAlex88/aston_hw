package by.alex.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private static final int INITIAL_CAPACITY = 10;
    private static final int CUSTOM_CAPACITY = 5;
    private static final int NEGATIVE_INDEX = -1;
    private static final int VALUE_ZERO = 0;
    private static final int VALUE_ONE = 1;
    private static final int VALUE_TWO = 2;
    private static final int VALUE_THREE = 3;


    private List<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @DisplayName("Constructing an empty list with default constructor")
    @Test
    void testDefaultConstructor() {
        assertEquals(VALUE_ZERO, list.size(), "Size should be 0 for empty list");
    }

    @DisplayName("Constructing a list with custom capacity")
    @Test
    void testCapacityConstructor() {
        MyArrayList<Integer> customList = new MyArrayList<>(CUSTOM_CAPACITY);
        assertEquals(VALUE_ZERO, customList.size(), "Size should be 0 after construction with custom capacity");
        for (int i = 0; i < CUSTOM_CAPACITY; i++) {
            customList.add(i);
        }
        assertEquals(CUSTOM_CAPACITY, customList.size(), "List should accept elements up to custom capacity");
    }

    @DisplayName("Constructing a list with negative capacity throws exception")
    @Test
    void testNegativeCapacityConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new MyArrayList<>(NEGATIVE_INDEX),
                "Constructing with negative capacity should throw IllegalArgumentException");
    }

    @DisplayName("Constructing a list from a collection")
    @Test
    void testCollectionConstructor() {
        MyArrayList<Integer> listFromCollection = new MyArrayList<>(Arrays.asList(VALUE_ONE, VALUE_TWO, VALUE_THREE));
        assertEquals(VALUE_THREE, listFromCollection.size(), "Size should be 3 after construction from collection");
        assertEquals(VALUE_ONE, listFromCollection.get(0), "First element should be 1");
        assertEquals(VALUE_TWO, listFromCollection.get(1), "Second element should be 2");
        assertEquals(VALUE_THREE, listFromCollection.get(2), "Third element should be 3");
    }

    @DisplayName("Constructing a list from a null collection throws exception")
    @Test
    void testNullCollectionConstructor() {
        assertThrows(NullPointerException.class,
                () -> new MyArrayList<>((java.util.Collection<Integer>) null),
                "Constructing from null collection should throw NullPointerException");
    }

    @DisplayName("Constructing a list from an array")
    @Test
    void testArrayConstructor() {
        Integer[] array = {VALUE_ONE, VALUE_TWO, VALUE_THREE};
        MyArrayList<Integer> listFromArray = new MyArrayList<>(array);
        assertEquals(VALUE_THREE, listFromArray.size(), "Size should be 3 after construction from array");
        assertEquals(VALUE_ONE, listFromArray.get(0), "First element should be 1");
        assertEquals(VALUE_TWO, listFromArray.get(1), "Second element should be 2");
        assertEquals(VALUE_THREE, listFromArray.get(2), "Third element should be 3");
    }

    @DisplayName("Constructing a list from a null array throws exception")
    @Test
    void testNullArrayConstructor() {
        assertThrows(NullPointerException.class,
                () -> new MyArrayList<>((Integer[]) null),
                "Constructing from null array should throw NullPointerException");
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
        MyArrayList<Object> objList = new MyArrayList<>();
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
        MyArrayList<Integer> smallList = new MyArrayList<>();
        for (int i = 0; i < (INITIAL_CAPACITY + 1); i++) {
            smallList.add(i);
        }
        assertEquals((INITIAL_CAPACITY + 1), smallList.size(), "Size should be 11 after adding 11 elements");
        for (int i = 0; i < (INITIAL_CAPACITY + 1); i++) {
            assertEquals(i, smallList.get(i), "Element at index " + i + " should be " + i);
        }
    }
}