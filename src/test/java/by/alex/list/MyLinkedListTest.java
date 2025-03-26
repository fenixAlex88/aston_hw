package by.alex.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("Constructing an empty list with default constructor")
    @Test
    void testDefaultConstructor() {
        assertEquals(VALUE_ZERO, list.size(), "Size should be 0 for empty list");
    }

    @DisplayName("Constructing a list from a collection")
    @Test
    void testCollectionConstructor() {
        MyLinkedList<Integer> listFromCollection = new MyLinkedList<>(Arrays.asList(VALUE_ONE, VALUE_TWO, VALUE_THREE));
        assertEquals(VALUE_THREE, listFromCollection.size(), "Size should be 3 after construction from collection");
        assertEquals(VALUE_ONE, listFromCollection.get(0), "First element should be 1");
        assertEquals(VALUE_TWO, listFromCollection.get(1), "Second element should be 2");
        assertEquals(VALUE_THREE, listFromCollection.get(2), "Third element should be 3");
    }

    @DisplayName("Constructing a list from a null collection throws exception")
    @Test
    void testNullCollectionConstructor() {
        assertThrows(NullPointerException.class,
                () -> new MyLinkedList<>((java.util.Collection<Integer>) null),
                "Constructing from null collection should throw NullPointerException");
    }

    @DisplayName("Constructing a list from an array")
    @Test
    void testArrayConstructor() {
        Integer[] array = {VALUE_ONE, VALUE_TWO, VALUE_THREE};
        MyLinkedList<Integer> listFromArray = new MyLinkedList<>(array);
        assertEquals(VALUE_THREE, listFromArray.size(), "Size should be 3 after construction from array");
        assertEquals(VALUE_ONE, listFromArray.get(0), "First element should be 1");
        assertEquals(VALUE_TWO, listFromArray.get(1), "Second element should be 2");
        assertEquals(VALUE_THREE, listFromArray.get(2), "Third element should be 3");
    }

    @DisplayName("Constructing a list from a null array throws exception")
    @Test
    void testNullArrayConstructor() {
        assertThrows(NullPointerException.class,
                () -> new MyLinkedList<>((Integer[]) null),
                "Constructing from null array should throw NullPointerException");
    }


    @DisplayName("Constructing a list from a collection")
    @Test
    void testConstructorWithCollection() {
        List<Integer> listFromCollection = new MyLinkedList<>(Arrays.asList(VALUE_ONE, VALUE_TWO, VALUE_THREE));
        assertEquals(VALUE_THREE, listFromCollection.size(), "Size should be 3 after construction from collection");
        assertEquals(VALUE_ONE, listFromCollection.get(0), "First element should be 1");
        assertEquals(VALUE_TWO, listFromCollection.get(1), "Second element should be 2");
        assertEquals(VALUE_THREE, listFromCollection.get(2), "Third element should be 3");
    }

    @DisplayName("Constructing a list from a null collection throws exception")
    @Test
    void testConstructorWithNullCollection() {
        Collection<Integer> nullCollection = null;
        assertThrows(NullPointerException.class,
                () -> new MyLinkedList<>(nullCollection),
                "Constructing from null collection should throw NullPointerException");
    }

    @DisplayName("Adding an element to the end of the list")
    @Test
    void testAddToEnd() {
        list.add(VALUE_ONE);
        assertEquals(VALUE_ONE, list.size(), "Size should be 1 after adding one element");
        assertEquals(VALUE_ONE, list.get(0), "First element should be the added value");
    }

    @DisplayName("Adding an element to the beginning of an empty list")
    @Test
    void testAddToEmptyListAtBeginning() {
        list.add(VALUE_ONE, 0);
        assertEquals(VALUE_ONE, list.size(), "Size should be 1 after adding to empty list");
        assertEquals(VALUE_ONE, list.get(0), "First element should be the added value");
    }

    @DisplayName("Adding an element to the middle of the list")
    @Test
    void testAddToMiddle() {
        list.add(VALUE_ONE);
        list.add(VALUE_THREE);
        list.add(VALUE_TWO, 1);
        assertEquals(VALUE_THREE, list.size(), "Size should be 3 after adding three elements");
        assertEquals(VALUE_ONE, list.get(0), "First element should be 1");
        assertEquals(VALUE_TWO, list.get(1), "Second element should be 2");
        assertEquals(VALUE_THREE, list.get(2), "Third element should be 3");
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

    @DisplayName("Removing the first element from a single-element list")
    @Test
    void testRemoveFirstFromSingleElementList() {
        list.add(VALUE_ONE);
        list.remove(VALUE_ONE);
        assertEquals(VALUE_ZERO, list.size(), "Size should be 0 after removing the only element");
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0),
                "Getting element from empty list should throw IndexOutOfBoundsException");
    }

    @DisplayName("Removing the last element from the list")
    @Test
    void testRemoveLastElement() {
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        list.remove(VALUE_TWO);
        assertEquals(VALUE_ONE, list.size(), "Size should be 1 after removing the last element");
        assertEquals(VALUE_ONE, list.get(0), "First element should be 1");
    }

    @DisplayName("Removing an element from the middle of the list")
    @Test
    void testRemoveElementFromMiddle() {
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        list.add(VALUE_THREE);
        list.remove(VALUE_TWO);
        assertEquals(VALUE_TWO, list.size(), "Size should be 2 after removing one element");
        assertEquals(VALUE_ONE, list.get(0), "First element should be 1");
        assertEquals(VALUE_THREE, list.get(1), "Second element should be 3");
    }

    @DisplayName("Removing a non-existent element")
    @Test
    void testRemoveNonExistentElement() {
        list.add(VALUE_ONE);
        list.remove(VALUE_TWO);
        assertEquals(VALUE_ONE, list.size(), "Size should remain 1 when removing non-existent element");
        assertEquals(VALUE_ONE, list.get(0), "First element should still be 1");
    }

    @DisplayName("Removing null element")
    @Test
    void testRemoveNullElement() {
        list.add(null);
        list.add(VALUE_ONE);
        list.remove(null);
        assertEquals(VALUE_ONE, list.size(), "Size should be 1 after removing null");
        assertEquals(VALUE_ONE, list.get(0), "First element should be 1");
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

    @DisplayName("Sorting a single non-comparable element does not throw exception")
    @Test
    void testSortSingleNonComparableElement() {
        List<Object> objList = new MyLinkedList<>();
        objList.add(new Object());
        assertDoesNotThrow(objList::sort,
                "Sorting a single non-comparable element should not throw an exception");
        assertEquals(VALUE_ONE, objList.size(), "Size should remain 1");
    }

    @DisplayName("Sorting a list with multiple non-comparable elements throws exception")
    @Test
    void testSortMultipleNonComparableElements() {
        List<Object> objList = new MyLinkedList<>();
        objList.add(new Object());
        objList.add(new Object());
        assertThrows(UnsupportedOperationException.class,
                objList::sort,
                "Sorting multiple non-comparable elements should throw UnsupportedOperationException");
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

    @DisplayName("Adding multiple elements to test list growth")
    @Test
    void testListGrowth() {
        for (int i = 0; i < (INITIAL_CAPACITY + 1); i++) {
            list.add(i);
        }
        assertEquals((INITIAL_CAPACITY + 1), list.size(), "Size should be 11 after adding 11 elements");
        for (int i = 0; i < (INITIAL_CAPACITY + 1); i++) {
            assertEquals(i, list.get(i), "Element at index " + i + " should be " + i);
        }
    }

    @DisplayName("Iterating over an empty list")
    @Test
    void testIteratorOnEmptyList() {
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext(), "Iterator should not have next element for empty list");
        assertThrows(NoSuchElementException.class,
                iterator::next,
                "Calling next() on empty iterator should throw NoSuchElementException");
    }

    @DisplayName("Iterating over a list with elements")
    @Test
    void testIteratorWithElements() {
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        list.add(VALUE_THREE);

        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext(), "Iterator should have next element");
        assertEquals(VALUE_ONE, iterator.next(), "First element should be 1");
        assertTrue(iterator.hasNext(), "Iterator should have next element");
        assertEquals(VALUE_TWO, iterator.next(), "Second element should be 2");
        assertTrue(iterator.hasNext(), "Iterator should have next element");
        assertEquals(VALUE_THREE, iterator.next(), "Third element should be 3");
        assertFalse(iterator.hasNext(), "Iterator should not have next element after last");
        assertThrows(NoSuchElementException.class,
                iterator::next,
                "Calling next() after last element should throw NoSuchElementException");
    }

    @DisplayName("Using for-each loop on a list")
    @Test
    void testForEachLoop() {
        list.add(VALUE_ONE);
        list.add(VALUE_TWO);
        list.add(VALUE_THREE);

        int[] expected = {VALUE_ONE, VALUE_TWO, VALUE_THREE};
        int index = 0;
        for (Integer value : list) {
            assertEquals(expected[index++], value, "Element at index " + (index - 1) + " should match");
        }
        assertEquals(VALUE_THREE, index, "Should iterate over all 3 elements");
    }
}