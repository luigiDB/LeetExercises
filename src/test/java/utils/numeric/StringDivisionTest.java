package utils.numeric;

import junit.framework.TestCase;

import java.util.function.BiFunction;

public class StringDivisionTest extends TestCase {
    public void testContinuousSubtractionsImplementation() {
        tests(StringDivision::divWithSubtraction);
    }

    public void testExponentialSumImplementation() {
        tests(StringDivision::divWithSum);
    }

    private void tests(BiFunction<String, String, String> function) {
        assertEquals("1", function.apply("1", "1"));
        assertEquals("0", function.apply("0", "1"));
        assertEquals("3", function.apply("10", "3"));
        assertEquals("0", function.apply("3", "6"));
        assertEquals("2", function.apply("24", "12"));
        assertEquals("-2", function.apply("-24", "12"));
        assertEquals("-2", function.apply("24", "-12"));
        assertEquals("2", function.apply("24", "12"));
    } 
}