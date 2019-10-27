package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.contains;

/*
You are given an integer array nums and you have to return a new counts array. The counts array has the property
where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 */
public class __315_CountOfSmallerNumbersAfterSelf {

    @Test
    public void test() {
        Assert.assertThat(countSmaller(new int[]{1, 5, 2, 6, 1, 1}), contains(0, 3, 2, 2, 0, 0));
    }

    public List<Integer> countSmaller(int[] nums) {
        if(nums.length == 0)
            return new ArrayList<>();
        Integer[] res = new Integer[nums.length];
        List<Integer> sortedList = new LinkedList<>();
        sortedList.add(nums[nums.length - 1]);
        res[nums.length - 1] = 0;

        for (int i = nums.length - 2; i >= 0; i--) {
            res[i] = insert(sortedList, nums[i]);
        }

        return Arrays.asList(res);
    }

    private int insert(List<Integer> list, int num) {
        //space inefficient store every inserted value
        int start = 0;
        int end = list.size() - 1;

        while (true) {
            int pivot = start + (end - start) / 2;
            //this to ensure leftmost insertion
            if (list.get(pivot) == num) {
                if (pivot > 0 && list.get(pivot - 1) == num) {
                    end = pivot;
                } else {
                    list.add(pivot, num);
                    return pivot;
                }
            }
            if (list.get(pivot) > num) {
                end = pivot - 1;
                if (end < start) {
                    list.add(end + 1, num);
                    return end + 1;
                }
            } else {
                start = pivot + 1;
                if (start > end) {
                    list.add(start, num);
                    return start;
                }
            }
        }
    }
}
