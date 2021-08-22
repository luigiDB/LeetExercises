package utils.numeric;

import java.math.BigInteger;

public class StringDivision {
    public static String divWithSubtraction(String a, String b) {
        if (a.equals("0"))
            return "0";

        BigInteger aInt = new BigInteger(a, 10);
        BigInteger bInt = new BigInteger(b, 10);

        boolean negativeResult = aInt.compareTo(BigInteger.ZERO) < 0 ^ bInt.compareTo(BigInteger.ZERO) < 0;
        aInt = aInt.abs();
        bInt = bInt.abs();

        int counter = 0;
        while (aInt.compareTo(bInt) >= 0) {
            aInt = aInt.subtract(bInt);
            counter++;
        }

        if (negativeResult)
            counter *= -1;

        return String.valueOf(counter);
    }

    public static String divWithSum(String a, String b) {
        if (a.equals("0"))
            return "0";

        BigInteger dividend = new BigInteger(a, 10);
        BigInteger divisor = new BigInteger(b, 10);

        return String.valueOf(divide(dividend.intValue(), divisor.intValue()));
    }

    private static int divide(int dividend, int divisor) {

        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        int quotient = 0;
        /* Once the divisor is bigger than the current dividend,
         * we can't fit any more copies of the divisor into it. */
        while (divisor >= dividend) {
            /* We know it'll fit at least once as divivend >= divisor.
             * Note: We use a negative powerOfTwo as it's possible we might have
             * the case divide(INT_MIN, -1). */
            int powerOfTwo = -1;
            int value = divisor;
            /* Check if double the current value is too big. If not, continue doubling.
             * If it is too big, stop doubling and continue with the next step */
            int HALF_INT_MIN = -1073741824;
            while (value >= HALF_INT_MIN && value + value >= dividend) {
                value += value;
                powerOfTwo += powerOfTwo;
            }
            // We have been able to subtract divisor another powerOfTwo times.
            quotient += powerOfTwo;
            // Remove value so far so that we can continue the process with remainder.
            dividend -= value;
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            return -quotient;
        }
        return quotient;
    }
}
