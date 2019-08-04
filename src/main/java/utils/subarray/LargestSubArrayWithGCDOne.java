package utils.subarray;

/**
 * https://www.geeksforgeeks.org/largest-subarray-gcd-one/
 * There is an array with n elements. Find length of the largest subarray having GCD equal to 1.
 * If no subarray with GCD 1, then print -1.
 */
public class LargestSubArrayWithGCDOne {

    /**
     * The trick is that if the global gcd is 1  there aren't zero in the array and all the elements are not the same.
     */
    public static int find(int[] input) {
        int gcd = input[0];

        for (int i = 1; i < input.length; i++) {
            gcd = gcd(gcd, input[i]);
        }

        return (gcd == 1) ? input.length : -1;
    }

    private static int gcd(int a, int b) {
        if (a == 0 || b == 0)
            return 0;

        if (a == b)
            return a;

        if (a > b)
            return gcd(a - b, b);

        return gcd(a, b - a);
    }
}
