package utils.numeric;

import junit.framework.TestCase;

public class ShuntingYardTest extends TestCase {

    private ShuntingYard shuntingYard;

    @Override
    public void setUp() throws Exception {
        shuntingYard = new ShuntingYard();
    }

    public void testBaseCases() {
        assertEquals(15, shuntingYard.eval("10+5"));
        assertEquals(5, shuntingYard.eval("10-5"));
        assertEquals(50, shuntingYard.eval("10*5"));
        assertEquals(2, shuntingYard.eval("10/5"));
    }

    public void testComplexOperations() {
        assertEquals(10, shuntingYard.eval("10+5*2/5-2"));
    }

    public void testParenthesis() {
        assertEquals(4, shuntingYard.eval("((10+5)*2/5)-2"));
    }
}