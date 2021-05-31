package facebook;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * One simple way to encrypt a string is to "rotate" every alphanumeric character by a certain amount. Rotating a
 * character means replacing it with another character that is a certain number of steps away in normal alphabetic or
 * numerical order.
 * For example, if the string "Zebra-493?" is rotated 3 places, the resulting string is "Cheud-726?". Every
 * alphabetic character is replaced with the character 3 letters higher (wrapping around from Z to A), and every
 * numeric character replaced with the character 3 digits higher (wrapping around from 9 to 0). Note that the
 * non-alphanumeric characters remain unchanged.
 * Given a string and a rotation factor, return an encrypted string.
 */
public class RotationalCipher {


    @Test
    public void a() {
        assertEquals("BAba10-", rotationalCipher("AZaz09-", 1));
    }

    String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if ('A' <= c && c <= 'Z')
                sb.append((char) ((((c - 'A') + rotationFactor) % ('Z' - 'A' + 1)) + 'A'));
            else if ('a' <= c && c <= 'z')
                sb.append((char) ((((c - 'a') + rotationFactor) % ('z' - 'a' + 1)) + 'a'));
            else if('0'<= c && c <= '9')
                sb.append((char)((((c-'0')+rotationFactor)%('9'-'0'+1))+'0'));
            else
                sb.append(c);
        }

        return sb.toString();
    }
}
