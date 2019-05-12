package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class _251_Flatten2DVector {
    /*Implement an iterator to flatten a 2d vector.
    For example, Given 2d vector =
    [
      [1,2],
      [3],
      [4,5,6]
    ]
    By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

    Follow up:
    As an added challenge, try to code it using only iterators in C++ or iterators in Java.*/

    @Test
    public void testSimplerCase() {
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> example = new ArrayList<>(Arrays.asList(1,2,3));
        input.add(example);

        List<Integer> flattered = flatter(input);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1,2,3)), flattered);
    }

    @Test
    public void testANormalExample() {
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> example = new ArrayList<>(Arrays.asList(1,2,3));
        input.add(example);
        List<Integer> example2 = new ArrayList<>(Arrays.asList(4));
        input.add(example2);
        List<Integer> example3 = new ArrayList<>(Arrays.asList(5,6));
        input.add(example3);

        List<Integer> flattered = flatter(input);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1,2,3,4,5,6)), flattered);
    }

    @Test
    public void testSimplerCaseWithIterator() {
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> example = new ArrayList<>(Arrays.asList(1,2,3));
        input.add(example);

        List<Integer> flattered = flatterWithIterator(input);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1,2,3)), flattered);
    }

    @Test
    public void testANormalExampleWithIterator() {
        List<List<Integer>> input = new ArrayList<>();
        List<Integer> example = new ArrayList<>(Arrays.asList(1,2,3));
        input.add(example);
        List<Integer> example2 = new ArrayList<>(Arrays.asList(4));
        input.add(example2);
        List<Integer> example3 = new ArrayList<>(Arrays.asList(5,6));
        input.add(example3);

        List<Integer> flattered = flatterWithIterator(input);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1,2,3,4,5,6)), flattered);
    }

    private List<Integer> flatter(List<List<Integer>> matrix) {
        List<Integer> flatteredView = new LinkedList<>();
        for (List<Integer> list : matrix) {
            for (Integer i: list) {
                flatteredView.add(i);
            }
        }
        return flatteredView;
    }

    private List<Integer> flatterWithIterator(List<List<Integer>> matrix) {
        List<Integer> flatterView = new LinkedList<>();

        Iterator<List<Integer>> outerIterator = matrix.iterator();
        while (outerIterator.hasNext()) {
            List<Integer> innerList = outerIterator.next();
            Iterator<Integer> innerIterator = innerList.iterator();
            while (innerIterator.hasNext()) {
                Integer element = innerIterator.next();
                flatterView.add(element);
            }
        }

        return flatterView;
    }
}
