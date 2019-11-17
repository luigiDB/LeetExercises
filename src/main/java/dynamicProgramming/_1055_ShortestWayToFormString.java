package dynamicProgramming;

import java.util.Arrays;

/*
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly
 * no deletions).
 * Given two strings source and target, return the minimum number of subsequences of source such that their
 * concatenation equals target. If the task is impossible, return -1.
 */
public class _1055_ShortestWayToFormString {

    /**
     * TODO:
     * Is the greedy solution viable? Are you able to write an example in which the greedy solution is not the
     * optimal solution.
     */
    /*
    public int greedySolution(String source, String target) {
        if (source.equals(target)) return 1;
        int k = source.length();
        int n = target.length();
        int ans = 0;
        int i = 0;
        while (i < n) {
            int base = i;
            int j = 0;
            while (j < k) {
                if (i < n && source.charAt(j) == target.charAt(i)) {
                    i++;
                }
                j++;
            }
            if (i == base) return -1;

            ans++;
        }
        return ans;
    }
     */
    public int evaluate(String source, String target) {
        int[] minSteps = new int[target.length()];
        Arrays.fill(minSteps, -1);
        return evaluate(source, target, 0, minSteps);
    }

    private int evaluate(String source, String target, int startIndex, int[] minSteps) {
        if (startIndex == target.length())
            return 0;
        if (minSteps[startIndex] != -1)
            return minSteps[startIndex];

        int minStepsFromStartIndex = Integer.MAX_VALUE;
        for (int i = startIndex; i < target.length(); i++) {
            if (isSubsequence(source, target.substring(startIndex, i + 1))) {
                int nextStepResult = evaluate(source, target, i + 1, minSteps);
                if (nextStepResult != -1)
                    minStepsFromStartIndex = Math.min(minStepsFromStartIndex, nextStepResult + 1);
            }
        }
        minSteps[startIndex] = (minStepsFromStartIndex == Integer.MAX_VALUE) ? -1 : minStepsFromStartIndex;
        return minSteps[startIndex];
    }

    private boolean isSubsequence(String source, String possibleSubsequence) {
        int sourceIndex = 0;
        for (int i = 0; i < possibleSubsequence.length(); i++) {
            char c = possibleSubsequence.charAt(i);
            while (sourceIndex < source.length() && c != source.charAt(sourceIndex))
                sourceIndex++;
            if (sourceIndex == source.length())
                return false;
            sourceIndex++;
        }
        return true;
    }
}
