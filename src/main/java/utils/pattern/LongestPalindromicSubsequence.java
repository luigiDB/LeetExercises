package utils.pattern;

/**
 * https://web.archive.org/web/20181208031518/https://articles.leetcode.com/longest-palindromic-substring-part-ii/
 * aka Manacher algorithm
 */
public class LongestPalindromicSubsequence {

    public static String find(String text) {
        char[] txt = preProcess(text).toCharArray();
        int txtLen = txt.length;
        int[] maxPalindromeLength = new int[txtLen];
        int C = 0;
        int R = 0;

        for (int i = 1; i < txtLen - 1; i++) {
            int i_mirror = 2 * C - i;
            maxPalindromeLength[i] = (R > i) ? Math.min(R - i, maxPalindromeLength[i_mirror]) : 0;

            // Attempt to expand palindrome centered at i
            while (txt[i + 1 + maxPalindromeLength[i]] == txt[i - 1 - maxPalindromeLength[i]])
                maxPalindromeLength[i]++;

            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + maxPalindromeLength[i] > R) {
                C = i;
                R = i + maxPalindromeLength[i];
            }
        }

        // Find the maximum element in maxPalindromeLength.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < txtLen - 1; i++) {
            if (maxPalindromeLength[i] > maxLen) {
                maxLen = maxPalindromeLength[i];
                centerIndex = i;
            }
        }

        return text.substring((centerIndex - 1 - maxLen) / 2, (centerIndex - 1 + maxLen) / 2);
    }

    private static String preProcess(String text) {
        int n = text.length();
        if (n == 0) return "^$";
        StringBuilder builder = new StringBuilder();
        builder.append("^");
        for (int i = 0; i < n; i++) {
            builder.append("#" + text.charAt(i));
        }

        builder.append("#$");
        return builder.toString();
    }
}
