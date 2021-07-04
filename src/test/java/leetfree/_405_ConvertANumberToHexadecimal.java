package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given an integer num, return a string representing its hexadecimal representation. For negative integers, two’s
 * complement method is used.
 * All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in
 * the answer except for the zero itself.
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 */
public class _405_ConvertANumberToHexadecimal {

    @Test
    public void a() {
        assertEquals("1a", toHex(26));
        assertEquals("ffffffff", toHex(-1));
        assertEquals("0", toHex(0));
    }

    public String toHex(int num) {
        if (num == 0)
            return "0";
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        int numCopy = num;
        StringBuilder sb = new StringBuilder();
        while (numCopy != 0) {
            int check = numCopy & 0b1111; // equivalent at numCopy % 16 but performed at bit level thus no arithmetic handling
            sb.insert(0, map[check]);
            numCopy = numCopy >>> 4; // equivalent at numCopy / 16 but a bit level
        }
        return sb.toString();
    }
}
