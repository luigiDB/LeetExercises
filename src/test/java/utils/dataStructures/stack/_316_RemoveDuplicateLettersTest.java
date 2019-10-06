package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

public class _316_RemoveDuplicateLettersTest {
    @Test
    public void test1() {
        Assert.assertEquals("abc", _316_RemoveDuplicateLetters.removeDuplicates("bcabc"));
    }

    @Test
    public void test2() {
        Assert.assertEquals("acdb", _316_RemoveDuplicateLetters.removeDuplicates("cbacdcbc"));
    }
}