package leetfree;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class __23_MergeKSortedLists {
    /**
     * 1_ we can apply the merge sorting by the book evaluating each time the min element among the k
     * 2_ We can apply the merge sorting two at the times this way the cost is O(log(N) * N ) because we pass all the
     * values log(N) times.
     */
}
