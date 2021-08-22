package utils.numeric;

import junit.framework.TestCase;

public class StringSumTest extends TestCase {
    public void testBase() {
        assertEquals("1", StringSum.sum("1", "0"));
        assertEquals("5", StringSum.sum("3", "2"));
        assertEquals("12", StringSum.sum("6", "6"));
        assertEquals("36", StringSum.sum("24", "12"));
        assertEquals("161", StringSum.sum("129", "32"));
    }
}