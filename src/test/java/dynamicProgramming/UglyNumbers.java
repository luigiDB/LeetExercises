package dynamicProgramming;

import org.junit.Test;

/**
 * https://www.geeksforgeeks.org/ugly-numbers/
 */
public class UglyNumbers {

    @Test
    public void findNthUglyNumber() {
        System.out.println(getNthUglyNo(7));
    }

    int getNthUglyNo(int n) {
        int ugly[] = new int[n];  // To store ugly numbers
        int i2 = 0, i3 = 0, i5 = 0;
        int next_multiple_of_2 = 2;
        int next_multiple_of_3 = 3;
        int next_multiple_of_5 = 5;
        int next_ugly_no = 1;

        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            next_ugly_no = Math.min(next_multiple_of_2,
                    Math.min(next_multiple_of_3,
                            next_multiple_of_5));

            ugly[i] = next_ugly_no;
            if (next_ugly_no == next_multiple_of_2) {
                i2 = i2 + 1;
                next_multiple_of_2 = (i2 + 1) * 2;
            }
            if (next_ugly_no == next_multiple_of_3) {
                i3 = i3 + 1;
                next_multiple_of_3 = (i3 + 1) * 3;
            }
            if (next_ugly_no == next_multiple_of_5) {
                i5 = i5 + 1;
                next_multiple_of_5 = (i5 + 1) * 5;
            }
        }
        return next_ugly_no;
    }
}
