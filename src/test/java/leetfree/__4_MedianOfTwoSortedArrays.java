package leetfree;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class __4_MedianOfTwoSortedArrays {
    /**
     * https://medium.com/@hazemu/finding-the-median-of-2-sorted-arrays-in-logarithmic-time-1d3f2ecbeb46
     * In practise once we determine how many (min and max) elements of the first array we can potentially use we can
     * iterate the next approach evaluating each time in the middle element of the remaining set.
     *
     * Suppose the two arrays are A and B and we can peek from MIN to MAX elements from A
     * Given that we want to test if x elements from A are a good split
     * if( A[x] > B[MAX - x + 1]) //NB we are confronting with the first excluded from B
     *      the next element from B should be included so search left
     * else
     *      if( A[x +1] < B[ MAX -x ] )
     *          the next element from A should be included so search right
     *      else
     *          We found the split.
     */
}
