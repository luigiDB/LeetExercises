package dynamicProgramming;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * A subsequence of a string is a new string generated from the original string with some characters(can be none)
 * deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde"
 * while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 */
public class _1143_LongestCommonSubsequence {

    public int LCS(String first, String second) {
        return LCS(first, second, first.length(), second.length());
    }

    private int LCS(String first, String second, int firstLen, int secondLen) {
        if (firstLen == 0 || secondLen == 0) {
            return 0;
        }
        if (first.charAt(firstLen - 1) == second.charAt(secondLen - 1)) {
            return LCS(first, second, firstLen - 1, secondLen - 1) + 1;
        }
        return Math.max(
                LCS(first, second, firstLen - 1, secondLen),
                LCS(first, second, firstLen, secondLen - 1)
        );
    }
}
