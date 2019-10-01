package leetfree;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class __128_LongestConsecutiveSequence {
    //TODO
    /**
     * Multiple possible implementations
     * 1_ If we can store in memory the array with size equals to the maximum number in the input array we can simply
     * use the linear sort
     * 2_ While reading the list we can encounter only 4 cases:
     *      1_ new number without neighbor
     *          Easy to add to the map a new value like x -> <x, x>
     *      2_ new min for a range <y, z>
     *          Change y to y-1 in the range
     *          Add y-1 -> <y-1, z> (point to the same range object as previous step)
     *          Delete key y
     *      3_ new max for a range
     *          As above but for right side
     *      4_ a merge for ranges <x, y> <y+2, z>
     *          Evaluate the range <x, z>
     *          set x -> <x, z>
     *          set z -> <x, z>
     *          Delete y and y+2, Do not insert y+1
     *      While updating the ranges we can keep track of the biggest range
     */
}
