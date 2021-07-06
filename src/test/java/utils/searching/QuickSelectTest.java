package utils.searching;

import junit.framework.TestCase;

public class QuickSelectTest extends TestCase {

    public void testThatTheCorrectElementIsFound() {
        assertEquals(4, QuickSelect.findKthSmallest(new int[] {7,4,6,3,9,1}, 2));
    }
}