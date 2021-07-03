package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given an array of characters chars, compress it using the following algorithm:
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * After you are done modifying the input array, return the new length of the array.
 * You must write an algorithm that uses only constant extra space.
 */
public class _443_StringCompression {

    @Test
    public void a() {
        char[] input = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int len = compress(input);
        assertEquals(
                "a2b2c3",
                arrayToString(input, len)
        );
    }

    @Test
    public void b() {
        char[] input = {'a'};
        int len = compress(input);
        assertEquals(
                "a",
                arrayToString(input, len)
        );
    }

    @Test
    public void c() {
        char[] input = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int len = compress(input);
        assertEquals(
                "ab12",
                arrayToString(input, len)
        );
    }

    @Test
    public void d() {
        char[] input = {'a', 'a', 'a', 'b', 'b', 'a', 'a'};
        int len = compress(input);
        assertEquals(
                "a3b2a2",
                arrayToString(input, len)
        );
    }

    public int compress(char[] chars) {
        int writePointer = 0;
        char current = '\u0000';
        int currentCounter = 0;
        for (int i = 0; i < chars.length; i++) {

            char currChar = chars[i];
            if (currChar != current) {
                if (current != '\u0000') {
                    chars[writePointer++] = current;
                    if (currentCounter != 1)
                        for (char c : Integer.toString(currentCounter).toCharArray())
                            chars[writePointer++] = c;
                }
                current = currChar;
                currentCounter = 1;
            } else {
                currentCounter++;
            }
        }


        chars[writePointer++] = current;
        if (currentCounter != 1)
            for (char c : Integer.toString(currentCounter).toCharArray())
                chars[writePointer++] = c;

        return writePointer;
    }

    private String arrayToString(char[] input, int limit) {
        StringBuilder sb = new StringBuilder();
        for (char c : input) {
            if (sb.length() == limit)
                break;
            sb.append(c);
        }
        return sb.toString();
    }
}
