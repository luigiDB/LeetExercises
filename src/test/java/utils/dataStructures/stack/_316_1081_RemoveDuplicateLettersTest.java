package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

public class _316_1081_RemoveDuplicateLettersTest {
    @Test
    public void test1() {
        Assert.assertEquals("abc", _316_1081_RemoveDuplicateLetters.removeDuplicates("bcabc"));
    }

    @Test
    public void test2() {
        Assert.assertEquals("acdb", _316_1081_RemoveDuplicateLetters.removeDuplicates("cbacdcbc"));
    }
}