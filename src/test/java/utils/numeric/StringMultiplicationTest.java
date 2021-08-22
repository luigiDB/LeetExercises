package utils.numeric;

import junit.framework.TestCase;

public class StringMultiplicationTest extends TestCase {

    public void testBase() {
        assertEquals("0", StringMultiplication.mul("1", "0"));
        assertEquals("6", StringMultiplication.mul("3", "2"));
        assertEquals("18", StringMultiplication.mul("3", "6"));
        assertEquals("288", StringMultiplication.mul("24", "12"));
        assertEquals("4128", StringMultiplication.mul("129", "32"));
    }
}