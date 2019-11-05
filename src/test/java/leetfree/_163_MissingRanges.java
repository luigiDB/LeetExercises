package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class _163_MissingRanges {
    /*Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
    For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].*/

    @Test
    public void testNoHole() {
        int[] example = new int[]{0, 1, 2, 3, 4};
        List<String> missingRanges = findMissingRanges(example, 0, 4);
        List<String> expectedResult = new LinkedList<>();
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testOneHole() {
        int[] example = new int[]{0, 1, 2, 4};
        List<String> missingRanges = findMissingRanges(example, 0, 4);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("3"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testRangeHole() {
        int[] example = new int[]{0, 1, 4};
        List<String> missingRanges = findMissingRanges(example, 0, 4);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("2->3"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testEndHole() {
        int[] example = new int[]{0, 1};
        List<String> missingRanges = findMissingRanges(example, 0, 4);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("2->4"));
        Assert.assertEquals(expectedResult, missingRanges);

        example = new int[]{0, 1};
        missingRanges = findMissingRanges(example, 0, 2);
        expectedResult = new LinkedList<>(Arrays.asList("2"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testStartHole() {
        int[] example = new int[]{3, 4};
        List<String> missingRanges = findMissingRanges(example, 0, 4);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("0->2"));
        Assert.assertEquals(expectedResult, missingRanges);

        example = new int[]{1, 2, 3, 4};
        missingRanges = findMissingRanges(example, 0, 4);
        expectedResult = new LinkedList<>(Arrays.asList("0"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testEmptyInput() {
        int[] example = new int[]{};
        List<String> missingRanges = findMissingRanges(example, 1, 2);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("1->2"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testNoMissingRange() {
        int[] example = new int[]{1};
        List<String> missingRanges = findMissingRanges(example, 1, 1);
        List<String> expectedResult = new LinkedList<>(Arrays.asList());
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testEmptyRange() {
        int[] example = new int[]{};
        List<String> missingRanges = findMissingRanges(example, 1, 1);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("1"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testArrayEqualsUpper() {
        int[] example = new int[]{-1};
        List<String> missingRanges = findMissingRanges(example, -2, -1);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("-2"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testMultipleHoles() {
        int[] example = new int[]{1,2,4,7};
        List<String> missingRanges = findMissingRanges(example, 0, 10);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("0", "3", "5->6", "8->10"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> resultingHoles = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            addRange(resultingHoles, lower, upper);
            return resultingHoles;
        }

        //starting hole
        if (lower != nums[0]) {
            addRange(resultingHoles, lower, nums[0] - 1);
        }

        int l = nums[0];
        //middle holes
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - l > 1) {
                addRange(resultingHoles, l + 1, nums[i] - 1);
            }
            l = nums[i];
        }

        //end hole
        if(upper != l) {
            addRange(resultingHoles, l+1, upper);
        }

        return resultingHoles;
    }

    private void addRange(List<String> collector, int low, int high) {
        if (low == high)
            collector.add(String.valueOf(low));
        else
            collector.add(low + "->" + high);
    }
}
