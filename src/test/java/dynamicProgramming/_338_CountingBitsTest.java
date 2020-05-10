package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class _338_CountingBitsTest {

    private _338_CountingBits countingBits;

    @Before
    public void setUp() throws Exception {
        countingBits = new _338_CountingBits();
    }

    @Test
    public void testGivenExamples() {
        Assert.assertArrayEquals(new int[]{0, 1, 1}, countingBits.counting(2));
        Assert.assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, countingBits.counting(5));
    }
}