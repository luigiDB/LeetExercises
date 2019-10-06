package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

public class _341_FlattenNestedListIteratorTest {
    @Test
    public void test1() {
        _341_FlattenNestedListIterator iterator = new _341_FlattenNestedListIterator(new int[][]{
                {1, 2},
                {3},
                {4, 5}
        });

        int[] expectedSolution = new int[]{1, 2, 3, 4, 5};
        for (int i = 0; i < expectedSolution.length; i++) {
            Assert.assertTrue(iterator.hasNext());
            Assert.assertEquals(expectedSolution[i], iterator.next());
        }
    }

}