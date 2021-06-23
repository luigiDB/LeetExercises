package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */
public class _246_StrobogrammaticNumber {

    @Test
    public void testTheseAreStrobogrammatic() {
        Assert.assertTrue(isStrobogrammatic("1111"));
        Assert.assertTrue(isStrobogrammatic("0000"));
        Assert.assertTrue(isStrobogrammatic("8888"));
        Assert.assertTrue(isStrobogrammatic("69"));
        Assert.assertTrue(isStrobogrammatic("6699"));
        Assert.assertTrue(isStrobogrammatic("606909"));
        Assert.assertTrue(isStrobogrammatic("66899"));
    }

    @Test
    public void testThatTheseAreNot() {
        Assert.assertFalse(isStrobogrammatic("2"));
        Assert.assertFalse(isStrobogrammatic("3457"));
        Assert.assertFalse(isStrobogrammatic("888121888"));
        Assert.assertFalse(isStrobogrammatic("66699"));
    }

    private boolean isStrobogrammatic(String number) {
        char[] ciphers = number.toCharArray();
        for (int i = 0; i < ciphers.length; i++) {
            if(isBannedChar(ciphers[i])) {
               return false;
            }
            if(!isSpecularCorrect(i, ciphers)){
                return false;
            }
        }
        return true;
    }

    private boolean isSpecularCorrect(int cipher, char[] ciphers) {
        char start = ciphers[cipher];
        char specular = ciphers[ciphers.length-1-cipher];
        switch (start){
            case '0':
                if(specular!='0') return false;
                break;
            case '1':
                if(specular!='1') return false;
                break;
            case '8':
                if(specular!='8') return false;
                break;
            case '6':
                if(specular!='9') return false;
                break;
            case '9':
                if(specular!='6') return false;
                break;
        }
        return true;
    }

    private boolean isBannedChar(char c) {
        switch (c) {
            case '2':
            case '3':
            case '4':
            case '5':
            case '7':
                return true;
        }
        return false;
    }
}
