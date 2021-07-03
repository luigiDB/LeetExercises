package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given
 * target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class _34_FindFirstAndLastPositionOfElementInSortedArray {
    @Test
    public void a() {
        int[] input = {5, 7, 7, 8, 8, 10};
        int[] expected = {3, 4};
        assertArrayEquals(expected, searchRange(input, 8));
    }

    @Test
    public void b() {
        int[] input = {5, 7, 7, 8, 8, 10};
        int[] expected = {-1, -1};
        assertArrayEquals(expected, searchRange(input, 6));
    }

    @Test
    public void c() {
        int[] input = {};
        int[] expected = {-1, -1};
        assertArrayEquals(expected, searchRange(input, 0));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[]{-1, -1};
        int first = searchFirst(nums, target);
        if (first == -1)
            return new int[]{-1, -1};
        return new int[]{first, searchLast(nums, target)};
    }

    private int searchFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left >= nums.length || nums[left] == target)
            return left;
        else
            return -1;
    }

    private int searchLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (nums[right] == target)
            return right;
        else
            return -1;
    }
}

