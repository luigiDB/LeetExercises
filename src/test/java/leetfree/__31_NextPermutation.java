package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 */
public class __31_NextPermutation {

    @Test
    public void a() {
        int[] inOut = {1, 2, 3};
        nextPermutation(inOut);
        Assert.assertArrayEquals(
                new int[]{1, 3, 2},
                inOut

        );
    }

    @Test
    public void b() {
        int[] inOut = {3, 2, 1};
        nextPermutation(inOut);
        Assert.assertArrayEquals(
                new int[]{1, 2, 3},
                inOut

        );
    }

    @Test
    public void c() {
        int[] inOut = {1, 1, 5};
        nextPermutation(inOut);
        Assert.assertArrayEquals(
                new int[]{1, 5, 1},
                inOut

        );
    }

    /**
     * 1_ from right find the first number in decreasing order a[i]
     * 2_ substitute a[i] with the smallest bigger number on the right
     * 3_ reverse the array from a[i+1] to a[n]
     */
    public void nextPermutation(int[] nums) {
        int firstDecreasing = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                firstDecreasing = i - 1;
                break;
            }
        }

        if (firstDecreasing == -1) {
            reverse(nums, 0);
            return;
        }

        for (int i = nums.length - 1; i > firstDecreasing; i--) {
            if (nums[i] > nums[firstDecreasing])
                swap(nums, firstDecreasing, i);
        }
        reverse(nums, firstDecreasing + 1);
    }

    private void reverse(int[] nums, int startIndex) {
        for (int i = startIndex; i <= startIndex + (nums.length - startIndex) / 2; i++) {
            swap(nums, i, nums.length - i + startIndex - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
