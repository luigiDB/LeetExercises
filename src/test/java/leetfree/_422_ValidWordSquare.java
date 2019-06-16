package leetfree;

import org.junit.Assert;
import org.junit.Test;

public class _422_ValidWordSquare {
    /**
     * Given a sequence of words, check whether it forms a valid word square.
     * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
     * Note:
     * The number of words given is at least 1 and does not exceed 500.
     * Word length will be at least 1 and does not exceed 500.
     * Each word contains only lowercase English alphabet a-z.
     * Example 1:
     * Input:
     * [
     * "abcd",
     * "bnrt",
     * "crmy",
     * "dtye"
     * ]
     * Output:
     * true
     * Explanation:
     * The first row and first column both read "abcd".
     * The second row and second column both read "bnrt".
     * The third row and third column both read "crmy".
     * The fourth row and fourth column both read "dtye".
     * Therefore, it is a valid word square.
     * Example 2:
     * Input:
     * [
     * "abcd",
     * "bnrt",
     * "crm",
     * "dt"
     * ]
     * Output:
     * true
     * Explanation:
     * The first row and first column both read "abcd".
     * The second row and second column both read "bnrt".
     * The third row and third column both read "crm".
     * The fourth row and fourth column both read "dt".
     * Therefore, it is a valid word square.
     * Example 3:
     * Input:
     * [
     * "ball",
     * "area",
     * "read",
     * "lady"
     * ]
     * Output:
     * false
     * Explanation:
     * The third row reads "read" while the third column reads "lead".
     * Therefore, it is NOT a valid word square.
     */

    @Test
    public void trueCases() {
        Assert.assertTrue(isValidSquare(new char[][]{{'a', 'b', 'c', 'd'},
                {'b', 'n', 'r', 't'},
                {'c', 'r', 'm', 'y'},
                {'d', 't', 'y', 'e'}}));
        Assert.assertTrue(isValidSquare(new char[][]{{'a', 'b', 'c', 'd'},
                {'b', 'n', 'r', 't'},
                {'c', 'r', 'm'},
                {'d', 't'}}));
    }

    @Test
    public void falseCases() {
        Assert.assertFalse(isValidSquare(new char[][]{{'b', 'a', 'l', 'l'},
                {'a', 'r', 'e', 'a'},
                {'r', 'e', 'a', 'd'},
                {'l', 'a', 'd', 'y'}}));
    }

    private boolean isValidSquare(char[][] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words[i].length; j++) {
                if(words[i][j] != words[j][i])
                    return false;
            }
        }

        return true;
    }
}
