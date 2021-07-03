package utils.searching;

import junit.framework.TestCase;

public class BinarySearchTest extends TestCase {

    public void testGenericSearch() {
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11};
        assertEquals(4, BinarySearch.search(arr, 4));
        assertEquals(10, BinarySearch.search(arr, 11));
        assertEquals(0, BinarySearch.search(arr, 0));

        assertEquals(-1, BinarySearch.search(arr, 7));
        assertEquals(-1, BinarySearch.search(arr, -1));
        assertEquals(-1, BinarySearch.search(arr, 20));
    }

    public void testFirstOccurrenceOfElement() {
        int[] arr = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        assertEquals(6, BinarySearch.firstOccurrence(arr, 1));

        arr = new int[]{0, 0, 0, 0, 0, 0};
        assertEquals(-1, BinarySearch.firstOccurrence(arr, 1));

        arr = new int[]{0, 0, 0, 0, 0, 2};
        assertEquals(-1, BinarySearch.firstOccurrence(arr, 1));
    }


    public void testLastOccurrenceOfElement() {
        int[] arr = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        assertEquals(10, BinarySearch.lastOccurrence(arr, 1));
        assertEquals(5, BinarySearch.lastOccurrence(arr, 0));

        arr = new int[]{0, 0, 0, 0, 0, 0};
        assertEquals(-1, BinarySearch.lastOccurrence(arr, 1));

        arr = new int[]{0, 0, 0, 0, 0, 2};
        assertEquals(-1, BinarySearch.lastOccurrence(arr, 1));

        arr = new int[]{5,7,7,8,8,9};
        assertEquals(4, BinarySearch.lastOccurrence(arr, 8));
    }
}