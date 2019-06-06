package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class _379_DesignPhoneDirectory {
    /**
     * Design a Phone Directory which supports the following operations:
     * get: Provide a number which is not assigned to anyone.
     * check: Check if a number is available or not.
     * release: Recycle or release a number.
     * Example:
     * // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
     * PhoneDirectory directory = new PhoneDirectory(3);
     * // It can return any available phone number. Here we assume it returns 0.
     * directory.get();
     * // Assume it returns 1.
     * directory.get();
     * // The number 2 is available, so return true.
     * directory.check(2);
     * // It returns 2, the only number that is left.
     * directory.get();
     * // The number 2 is no longer available, so return false.
     * directory.check(2);
     * // Release number 2 back to the pool.
     * directory.release(2);
     * // Number 2 is available again, return true.
     * directory.check(2);
     */

    @Test
    public void givenExample() {
        PhoneDirectory directory = new PhoneDirectory(3);
        Assert.assertEquals(0, directory.get());
        Assert.assertEquals(1, directory.get());
        Assert.assertTrue(directory.check(2));
        Assert.assertEquals(2, directory.get());
        Assert.assertFalse(directory.check(2));
        directory.release(2);
        Assert.assertTrue(directory.check(2));
    }

    private class PhoneDirectory {

        private int[] available;
        private int max;
        private int nextAvailable;

        public PhoneDirectory(int i) {
            available = new int[i];
            max = i;
            nextAvailable = 0;
        }

        public int get() {
            if (available[0] != 0) {
                int tmp = available[0];
                available[0] = available[tmp];
                available[tmp] = 0;
                return tmp;
            }
            if (nextAvailable < max)
                return nextAvailable++;
            throw (new RuntimeException("overflow"));
        }

        public boolean check(int i) {
            if (available[0] != 0)
                return available[i] == 0;
            return i >= nextAvailable;
        }

        public void release(int i) {
            int tmp = available[0];
            available[0] = i;
            available[i] = tmp;
        }
    }
}
