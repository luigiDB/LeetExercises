package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class _356_LineReflections {
    /*
    * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

    Example 1:
    Given points = [[1,1],[-1,1]], return true.

    Example 2:
    Given points = [[1,1],[-1,-1]], return false.

    Follow up:
    Could you do better than O(n2)?*/

    @Test
    public void trueCases() {
        Assert.assertTrue(existVerticalReflectionLine(new int[][]{{1, 1}, {-1, 1}}));
        Assert.assertTrue(existVerticalReflectionLine(new int[][]{{1, 1}, {-1, 1}, {2, 1}, {-2, 1}}));
        Assert.assertTrue(existVerticalReflectionLine(new int[][]{{1, 1}, {-1, 1}, {2, 1}, {-2, 1}, {-2, 1}}));
    }

    @Test
    public void falseCases() {
        Assert.assertFalse(existVerticalReflectionLine(new int[][]{{1, 1}, {-1, -1}}));
        Assert.assertFalse(existVerticalReflectionLine(new int[][]{{1, 1}, {-1, -1}, {3, 2}}));
        Assert.assertFalse(existVerticalReflectionLine(new int[][]{{1, 1}, {-1, 1}, {2, 1}, {-2, 1}, {-3, -1}}));
    }

    private boolean existVerticalReflectionLine(int[][] input) {
        Map<Integer, SortedSet<int[]>> ySegregation = new HashMap<>();
        for (int[] elem : Arrays.asList(input)) {
            ySegregation.computeIfAbsent(elem[1], k -> new TreeSet<>(
                    Comparator.comparingInt((int[] o) -> o[1]).thenComparing((int[] j) -> j[0])
            )).add(elem);
        }
        Double outerCandidate = null;
        for (SortedSet line : ySegregation.values()) {
            Double inner = evaluateInnerCandidate(line);
            if (inner == null)
                return false;
            if (outerCandidate == null)
                outerCandidate = inner;
            else if (!outerCandidate.equals(inner))
                return false;
        }

        return true;
    }

    Double evaluateInnerCandidate(SortedSet<int[]> line) {
        if (line.size() % 2 == 1)
            return null;
        Double innerCandidate = null;
        ArrayList<int[]> arrayList = new ArrayList<>(line);
        for (int i = 0; i < arrayList.size() / 2; i++) {
            double middle = (arrayList.get(i)[0] + arrayList.get(arrayList.size() - 1 - i)[0]) / 2;
            if (innerCandidate == null)
                innerCandidate = middle;
            else if (!innerCandidate.equals(middle))
                return null;
        }
        return innerCandidate;
    }
}
