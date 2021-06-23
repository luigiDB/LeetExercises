package leetfree;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class _349_IntersectionOfTwoArrays {

    @Test
    public void a() {
        int[] res = intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        assertEquals(2, res.length);
        assertTrue(Arrays.equals(new int[]{9, 4}, res) || Arrays.equals(new int[]{4, 9}, res));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> _1 = new HashSet<>();
        Set<Integer> _2 = new HashSet<>();
        Arrays.stream(nums1).boxed().forEach(_1::add);
        Arrays.stream(nums2).boxed().forEach(_2::add);

        _1.retainAll(_2);
        int[] res = new int[_1.size()];
        int i = 0;
        for (Integer num : _1) {
            res[i++] = num;
        }
        return res;
    }


    /*
    This is a Facebook interview question.
    They ask for the intersection, which has a trivial solution using a hash or a set.

    Then they ask you to solve it under these constraints:
    O(n) time and O(1) space (the resulting array of intersections is not taken into consideration).
    You are told the lists are sorted.

    Cases to take into consideration include:
    duplicates, negative values, single value lists, 0's, and empty list arguments.
    Other considerations might include
    sparse arrays.
     */

    @Test
    public void a_facebook() {
        Integer[] res = orderedIntersection(new int[]{4, 5, 9}, new int[]{4, 4, 8, 9, 9});
        assertArrayEquals(new Integer[]{4, 9}, res);
    }

    public Integer[] orderedIntersection(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();

        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if(nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else {
                if(nums1[i] < nums2[j])
                    i++;
                else
                    j++;
            }
        }
        return res.toArray(new Integer[0]);
    }
}
