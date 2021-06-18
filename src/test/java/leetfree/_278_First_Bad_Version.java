package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version
 * of your product fails the quality check. Since each version is developed based on the previous version, all the
 * versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the
 * following ones to be bad.
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find
 * the first bad version. You should minimize the number of calls to the API.
 */
public class _278_First_Bad_Version {

    @Test
    public void a() {
        assertEquals(4, new Solution(4).firstBadVersion(5));
    }

    public class Solution extends VersionControl {

        public Solution(int startingBadVersion) {
            super(startingBadVersion);
        }

        public int firstBadVersion(int n) {
            int left = 1, rigth = n;
            while (left <= rigth) {
                int pivot = (left + rigth) / 2;

                if (isBadVersion(pivot))
                    rigth = pivot - 1;
                else
                    left = pivot + 1;
            }
            return left;
        }
    }

    class VersionControl {

        private final int startingBadVersion;

        public VersionControl(int startingBadVersion) {
            this.startingBadVersion = startingBadVersion;
        }

        boolean isBadVersion(int version) {
            return version >= startingBadVersion;
        }
    }
}
