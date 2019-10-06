package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

public class _150_EvaluateReversePolishNotationTest {

    @Test
    public void sumTest() {
        Assert.assertEquals(9, _150_EvaluateReversePolishNotation.evaluate(new String[]{"5", "4", "+"}));
    }

    @Test
    public void difTest() {
        Assert.assertEquals(1, _150_EvaluateReversePolishNotation.evaluate(new String[]{"5", "4", "-"}));
    }

    @Test
    public void given1() {
        Assert.assertEquals(9, _150_EvaluateReversePolishNotation.evaluate(new String[]{"2", "1", "+", "3", "*"}));
    }

    @Test
    public void given2() {
        Assert.assertEquals(6, _150_EvaluateReversePolishNotation.evaluate(new String[]{"4", "13", "5", "/", "+"}));
    }
}