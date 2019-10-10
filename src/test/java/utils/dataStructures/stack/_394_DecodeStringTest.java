package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _394_DecodeStringTest {

    private _394_DecodeString decodeString;

    @Before
    public void setUp() throws Exception {
        decodeString = new _394_DecodeString();
    }

    @Test
    public void test1() {
        Assert.assertEquals("aaabcbc", decodeString.decode("3[a]2[bc]"));
    }

    @Test
    public void test2() {
        Assert.assertEquals("accaccacc", decodeString.decode("3[a2[c]]"));
    }

    @Test
    public void test3() {
        Assert.assertEquals("abcabccdcdcdef", decodeString.decode("2[abc]3[cd]ef"));
    }
}