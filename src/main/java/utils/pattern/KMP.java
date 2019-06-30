package utils.pattern;

import java.util.LinkedList;
import java.util.List;

/**
 * Time complexity O(n)
 */
public class KMP {
    /**
     * https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
     */

    public static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new LinkedList<>();

        int[] lps = computeLPSArray(pattern);
        int j = 0;

        for (int i = 0; i < text.length();) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == pattern.length()) {
                matches.add(i - j);
                j = lps[j - 1];
            } else {
                if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                    if (j != 0)
                        j = lps[j - 1];
                    else
                        i++;
                }
            }
        }

        return matches;
    }

    private static int[] computeLPSArray(String pat) {
        int lps[] = new int[pat.length()];
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar to search step.
                if (len != 0) {
                    len = lps[len - 1];
                    // Also, note that we do not increment i here
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
