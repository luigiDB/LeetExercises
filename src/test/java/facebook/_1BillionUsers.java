package facebook;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * We have N different apps with different user growth rates. At a given time t, measured in days, the number of users
 * using an app is g^t (for simplicity we'll allow fractional users), where g is the growth rate for that app. These
 * apps will all be launched at the same time and no user ever uses more than one of the apps. We want to know how many
 * total users there are when you add together the number of users from each app.
 * After how many full days will we have 1 billion total users across the N apps?
 */
public class _1BillionUsers {

    @Test
    public void a() {
        assertEquals(52, getBillionUsersDay(new float[]{1.5F}));
        assertEquals(79, getBillionUsersDay(new float[]{1.1F, 1.2F, 1.3F}));
        assertEquals(1047, getBillionUsersDay(new float[]{1.01F, 1.02F}));
    }

    @Test
    public void b() {
        assertEquals(52, getBillionUsersDayKnowingAnUpperBound(new float[]{1.5F}));
        assertEquals(79, getBillionUsersDayKnowingAnUpperBound(new float[]{1.1F, 1.2F, 1.3F}));
        assertEquals(1047, getBillionUsersDayKnowingAnUpperBound(new float[]{1.01F, 1.02F}));
    }

    int getBillionUsersDay(float[] growthRates) {
        // Write your code here
        float[] support = Arrays.copyOf(growthRates, growthRates.length);

        float sum = 0;
        int counter = 0;
        while (sum < 1000000000) {
            sum = 0;
            for (int i = 0; i < growthRates.length; i++) {
                sum += support[i];
                support[i] *= growthRates[i];
            }
            counter++;
        }
        return counter;
    }

    int getBillionUsersDayKnowingAnUpperBound(float[] growthRates) {
        // Write your code here
        int left = 0;
        int right = 2000;

        while (left < right) {
            int pivot = (right + left) / 2;

            float sum = 0;
            for (float growthRate : growthRates) {
                sum += (Math.pow(growthRate, pivot));
            }

            if (sum == 1_000_000_000)
                return pivot;
            else if (sum < 1_000_000_000)
                left = pivot + 1;
            else
                right = pivot - 1;
        }
        return left;
    }


}
