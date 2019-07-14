package leetfree;

import org.junit.Assert;
import org.junit.Test;

public class _487_MaxConsecutiveOnesII {
    /**
     * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
     * <p>
     * Example 1:
     * Input: [1,0,1,1,0]
     * Output: 4
     * Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
     * After flipping, the maximum number of consecutive 1s is 4.
     * Note:
     * <p>
     * The input array will only contain 0 and 1.
     * The length of input array is a positive integer and will not exceed 10,000
     * Follow up:
     * What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers
     * coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
     */



    public int maxOnes(int[] in) {
        int max = 0;
        int sumAtLastZero = 0;
        int sum = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 1) {
                sum++;
                max = Math.max(max, sumAtLastZero + 1 + sum);
            } else {
                sumAtLastZero = sum;
                sum = 0;

            }
        }
        return max;
    }

    @Test
    public void tests() {
        Assert.assertEquals(4,maxOnes(new int[]{1,0,1,1,0}));
        Assert.assertEquals(3,maxOnes(new int[]{1,0,1}));
        Assert.assertEquals(2,maxOnes(new int[]{1,0,0,1}));
        Assert.assertEquals(5,maxOnes(new int[]{1,0,1,0,1,1,0,1,1}));
        Assert.assertEquals(2,maxOnes(new int[]{1,0}));
    }
}
