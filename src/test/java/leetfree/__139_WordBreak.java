package leetfree;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class __139_WordBreak {
    /**
     * Two possible ways:
     * 1_ Recursive search if s starts with a word and recurse on the s.substring(word.length)
     * 2_ Use a Suffix tree and search all the occurrence for each word and create an ordered list of them like
     * the job scheduling problem. At this point we can search to find if there are contiguous ranges.
     *      If the last element in the rage is [x, y] and the new one is [k, z] we can merge using the following rules:
     *      1_ k == y+1 add to list
     *      2_ x > k <= y drop
     *      3_ k <= x there two routes:
     *          1_ drop the range
     *          2_ remove [x, y] and return to point 1  <== i'm not sure on this step surely the first approach works
     */
}
