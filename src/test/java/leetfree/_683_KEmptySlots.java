package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In
 * each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 * <p>
 * Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the
 * flower will open in that day.
 * <p>
 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and
 * x will be in the range from 1 to N.
 * <p>
 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and
 * also the number of flowers between them is k and these flowers are not blooming.
 * <p>
 * If there isn't such day, output -1.
 * <p>
 * Example 1:
 * Input:
 * flowers: [1,3,2]
 * k: 1
 * Output: 2
 * Explanation: In the second day, the first and the third flower have become blooming.
 * Example 2:
 * Input:
 * flowers: [1,2,3]
 * k: 1
 * Output: -1
 * Note:
 * The given array will be in the range [1, 20000].
 */
public class _683_KEmptySlots {

    /**
     * DISCLAIMER
     * The exercise require all array to indexed from 1 to N
     * to have everithing more clear I used in the test cases o indexed arrays but the same can be achieved by fixing
     * indexing and out of bound check. Or the easy way input the array as is and subtract 1 to each element and add 1
     * to result.
     */
    @Test
    public void possibleTest1() {
//        Assert.assertEquals(2, kEmptySlot(new int[]{1, 3, 2}, 1));
        Assert.assertEquals(1, kEmptySlot(new int[]{0, 2, 1}, 1));
    }

    @Test
    public void impossibleTest1() {
//        Assert.assertEquals(-1, kEmptySlot(new int[]{1, 2, 3}, 1));
        Assert.assertEquals(-1, kEmptySlot(new int[]{0, 1, 2}, 1));
    }

    @Test
    public void possibleTest2() {
//        Assert.assertEquals(3, kEmptySlot(new int[]{1, 4, 2, 6, 3, 5}, 1));
        Assert.assertEquals(2, kEmptySlot(new int[]{0, 3, 1, 5, 2, 4}, 1));
    }

    private int kEmptySlot(int[] flowers, int k) {
        int[] inverseFlower = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            inverseFlower[flowers[i]] = i;
        }

        for (int i = 0; i < flowers.length; i++) {
            int pivot = flowers[i];

            if (pivot - k - 1 > 0) {
                int left = inverseFlower[pivot - k - 1];
                int maxDay = Math.max(left, inverseFlower[pivot]);
                if (checkKMiddleElements(k, inverseFlower, pivot - k - 1 + 1, pivot, maxDay)) {
                    return maxDay;
                }
            }

            if (pivot + k + 1 < flowers.length) {
                int right = inverseFlower[pivot + k + 1];
                int maxDay = Math.max(inverseFlower[pivot], right);
                if (checkKMiddleElements(k, inverseFlower, pivot + 1, pivot + k + 1, maxDay)) {
                    return maxDay;
                }
            }

        }

        return -1;
    }

    private boolean checkKMiddleElements(int k, int[] inverseFlower, int left, int rigth, int maxDay) {
        for (int j = left; j < rigth; j++) {
            if (inverseFlower[j] < maxDay) {
                return false;
            }
        }
        return true;
    }
}
