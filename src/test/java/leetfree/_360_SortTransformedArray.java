package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _360_SortTransformedArray {
    /*Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.
    The returned array must be in sorted order.
    Expected time complexity: O(n)
    Example:
    nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
    Result: [3, 9, 15, 33]
    nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
    Result: [-23, -5, 1, 7]*/

    @Test
    public void tests() {
        Assert.assertEquals(Arrays.asList(3,9,15,33), sta(Arrays.asList(-4,-2,2,4),1,3,5));
        Assert.assertEquals(Arrays.asList(-23,-5,1,7), sta(Arrays.asList(-4,-2,2,4),-1,3,5));
    }

    private List<Integer> sta(List<Integer> input, int a, int b, int c) {
        return input.stream().map(i -> a*i*i + b*i + c).sorted().collect(Collectors.toList());
    }
}
