package greedy;

import org.junit.Test;
import utils.Subset;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * https://www.geeksforgeeks.org/minimum-product-subset-array/
 */
public class MinimumProductSubsetOfAnArray {

    @Test
    public void testSubset() {
        Subset subset = new Subset();

        List<List<Integer>> subset1 = subset.evaluateSubset(new int[]{1, 2, 3});

        for (List<Integer> internal : subset1) {
            System.out.println(internal.toString());
        }
    }

    @Test
    public void testAllCombinations() {
        Subset subset = new Subset();

        List<List<Integer>> subset1 = subset.evaluateSubset(new int[]{-1, -1, -2, 4, 3});

        List<Integer> minArray = null;
        int min = Integer.MAX_VALUE;
        for (List<Integer> internal : subset1) {
            Integer reduce = internal.stream().reduce(1, (first, second) -> first * second);
            if(reduce < min) {
                minArray = internal;
                min = reduce;
            }
        }

        System.out.println(minArray + "\t\t" + min);
    }

    @Test
    public void testAllCombinationsSecondExample() {
        Subset subset = new Subset();

        List<List<Integer>> subset1 = subset.evaluateSubset(new int[]{-1, 0});

        List<Integer> minArray = null;
        int min = Integer.MAX_VALUE;
        for (List<Integer> internal : subset1) {
            Integer reduce = internal.stream().reduce(1, (first, second) -> first * second);
            if(reduce < min) {
                minArray = internal;
                min = reduce;
            }
        }

        System.out.println(minArray + "\t\t" + min);
    }


    @Test
    public void testGreedyApproach() {
        Subset subset = new Subset();

        Integer[] input = {-1, -1, -2, 4, 3, 0};
        Map<Integer, List<Integer>> map = Arrays.stream(input).collect(Collectors.groupingBy(o -> o.compareTo(0)));

        Integer product = null;
        if(!map.get(-1).isEmpty()) {
            product = map.get(-1).stream().reduce((i1, i2) -> i1*i2).get();
            if(map.get(-1).size()%2==0){
                OptionalInt min = map.get(-1).stream().mapToInt(v -> v).min();
                product *= (1 / min.getAsInt());
            }
        }
        if(product != null && map.get(0).size()!=0) {
            product *= map.get(1).stream().reduce((i1, i2) -> i1*i2).get();
        }
        if(product==null)
            product = 0;

        System.out.println(product);
    }

}
