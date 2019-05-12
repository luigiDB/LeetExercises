package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _163_MissingRanges {
    /*Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
    For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].*/

    @Test
    public void testNoHole() {
        List<Integer> exemple = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<String> missingRanges = missingRanges(exemple, 0, 4);
        List<String> expectedResult = new LinkedList<>();
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testOneHole() {
        List<Integer> exemple = new LinkedList<>(Arrays.asList(0, 1, 2, 4));
        List<String> missingRanges = missingRanges(exemple, 0, 4);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("3"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testRangeHole() {
        List<Integer> exemple = new LinkedList<>(Arrays.asList(0, 1, 4));
        List<String> missingRanges = missingRanges(exemple, 0, 4);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("2->3"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testEndRangeHole() {
        List<Integer> exemple = new LinkedList<>(Arrays.asList(0, 1));
        List<String> missingRanges = missingRanges(exemple, 0, 4);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("2->4"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    @Test
    public void testStartRangeHole() {
        List<Integer> exemple = new LinkedList<>(Arrays.asList(3, 4));
        List<String> missingRanges = missingRanges(exemple, 0, 4);
        List<String> expectedResult = new LinkedList<>(Arrays.asList("0->2"));
        Assert.assertEquals(expectedResult, missingRanges);
    }

    private List<String> missingRanges(List<Integer> list, int lower, int upper) {
        List<String> resultingHoles = new LinkedList<>();
        int actualMin = lower;
        for (Integer i : list) {
            int jumpSize = i - actualMin;
            if (jumpSize > 1) {
                int startHole = actualMin + 1;
                if (actualMin == lower)
                    startHole = lower;
                int endHole = i - 1;
                if (startHole == endHole)
                    resultingHoles.add(String.valueOf(startHole));
                else
                    resultingHoles.add(startHole + "->" + endHole);
            }
            actualMin = i;
        }
        if (list.get(list.size() - 1) < upper) {
            resultingHoles.add(list.get(list.size()-1)+1 + "->" + upper);
        }
        return resultingHoles;
    }
}
