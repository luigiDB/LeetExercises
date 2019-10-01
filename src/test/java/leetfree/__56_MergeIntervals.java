package leetfree;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class __56_MergeIntervals {
    //TODO:
    // Can be resolved as the job scheduler with complexity O(N logN) + O(N)
    // or without reordering by keeping a list of the intervals ordered for the start or finish time and inserting
    // the new element with binary search (remember that when an interval is enlarged we need to check if the next
    // intervals are to be coalesced in the first one). Complexity O(N logN)  [N for the input and logN for the
    // insertion with binary search]
}
