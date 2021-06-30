package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks,
 * return the index to any of the peaks.
 * You may imagine that nums[-1] = nums[n] = -∞.
 * You must write an algorithm that runs in O(log n) time.
 */

/**
 * Non intuitive divide and conquer algorithm with binary search.
 * For explanation follow this MIT video (min 27:40)
 * https://www.youtube.com/watch?v=HtSuA80QTyo&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb&index=3
 */
public class _162_FindPeakElement {

    @Test
    public void a() {
        assertEquals(2, findPeakElement(new int[]{1, 2, 3, 1}));
    }

    @Test
    public void b() {
        assertEquals(5, findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
    }

    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    private int search(int[] nums, int l, int r) {
        int mid = (l + r) / 2;
        if (nums[mid - 1] > nums[mid])
            return search(nums, l, r - 1);
        else if (nums[mid] < nums[mid + 1])
            return search(nums, l + 1, r);
        else
            return mid;

    }
}
