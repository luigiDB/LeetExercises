package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that
 * the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For
 * example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * Given the array nums after the rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class _33_SearchInRotatedSortedArray {

    private final int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};

    @Test
    public void a() {
        assertEquals(4, search(nums, 0));
    }

    @Test
    public void b() {
        assertEquals(2, search(nums, 6));
    }

    @Test
    public void c() {
        assertEquals(-1, search(nums, 15));
    }

    public int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }


        }

        return -1;
    }
}
