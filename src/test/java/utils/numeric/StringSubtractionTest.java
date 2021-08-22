package utils.numeric;

import junit.framework.TestCase;

public class StringSubtractionTest extends TestCase {

    public void testBase() {
        assertEquals("1", StringSubtraction.sub("1", "0"));
        assertEquals("8", StringSubtraction.sub("10", "2"));
        assertEquals("-3", StringSubtraction.sub("3", "6"));
        assertEquals("12", StringSubtraction.sub("24", "12"));
    }
}