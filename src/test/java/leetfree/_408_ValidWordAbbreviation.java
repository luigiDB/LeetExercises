package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 * A string such as "word" contains only the following valid abbreviations:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a
 * valid abbreviation of "word".
 * Note:
 * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
 * Example 1:
 * Given s = "internationalization", abbr = "i12iz4n":
 * Return true.
 * Example 2:
 * Given s = "apple", abbr = "a2e":
 * Return false.
 */
public class _408_ValidWordAbbreviation {

    @Test
    public void validation() {
        Assert.assertTrue(isValidAbbreviation("internationalization", "i12iz4n"));
        Assert.assertFalse(isValidAbbreviation("apple", "a2e"));
        Assert.assertTrue(isValidAbbreviation("apple", "4e"));
    }

    private boolean isValidAbbreviation(String word, String abbreviation) {
        char[] aSplit = abbreviation.toCharArray();
        char[] wSplit = word.toCharArray();
        int wIndex = 0;
        for (int i = 0; i < aSplit.length; i++) {
            if (Character.isDigit(aSplit[i])) {
                int steps = Character.getNumericValue(aSplit[i]);
                int j = i + 1;
                for (; j < aSplit.length && Character.isDigit(aSplit[j]); j++) {
                    steps = steps * 10 + Character.getNumericValue(aSplit[j]);
                }
                wIndex += steps;
                i = j - 1;
            } else {
                if (wSplit[wIndex] != aSplit[i]) {
                    return false;
                } else {
                    wIndex++;
                }
            }
        }
        return true;
    }

}
