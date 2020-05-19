package utils.dataStructures.stack;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;

public class _901_OnlineStockSpanTest extends TestCase {

    private _901_OnlineStockSpan stockSpan;

    @Before
    public void setUp() throws Exception {
        stockSpan = new _901_OnlineStockSpan();
    }

    public void testGiven() {
        int[] inputs = new int[]{100, 80, 60, 70, 60, 75, 85};
        int[] expectation = new int[]{1, 1, 1, 2, 1, 4, 6};
        for (int i = 0; i < inputs.length; i++) {
            Assert.assertEquals(expectation[i], stockSpan.next(inputs[i]));
        }
    }
}