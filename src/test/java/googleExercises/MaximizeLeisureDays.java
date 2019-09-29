package googleExercises;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Leet code 568
 */
public class MaximizeLeisureDays {

    HashMap<Integer, List<Integer>> map = new HashMap<>();
    int holidays[][] = new int[][]{{2, 3, 3}, {3, 2, 3}};
    List<Integer> maxList;
    int maxValue;

    @Before
    public void setUp() throws Exception {
        map.put(null, Arrays.asList(1, 2));
        map.put(1, Arrays.asList(1, 2));
        map.put(2, Arrays.asList(1, 2));

        maxValue = 0;
    }

    @Test
    public void dynamicSolution() {
        int flights[][] = new int[][]{{0, 1}, {1, 0}};

        int i = maxVacationDays(flights, holidays);
        System.out.println(i);
    }


    public int maxVacationDays(int[][] flights, int[][] days) {
        if (days.length == 0 || flights.length == 0) return 0;
        int[][] dp = new int[days.length][days[0].length + 1];
        for (int week = days[0].length - 1; week >= 0; week--) {
            for (int cur_city = 0; cur_city < days.length; cur_city++) {
                dp[cur_city][week] = days[cur_city][week] + dp[cur_city][week + 1];
                for (int dest_city = 0; dest_city < days.length; dest_city++) {
                    if (flights[cur_city][dest_city] == 1) {
                        dp[cur_city][week] = Math.max(days[dest_city][week] + dp[dest_city][week + 1], dp[cur_city][week]);
                    }
                }
            }
        }
        return dp[0][0];
    }


    @Test
    public void evaluateAll() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        foo(list, 0);
        System.out.println(maxList + "\t " + maxValue);
    }

    @Test
    public void evaluateAll2() {
        int fooWithoutList = fooWithoutList(null, 0);
        System.out.println(fooWithoutList);

    }

    private void foo(LinkedList<Integer> l, int month) {

        if (month == holidays[0].length) {
            int evaluate = evaluate(l);
            System.out.println(l + "\t " + evaluate);
            if (evaluate > maxValue) {
                maxValue = evaluate;
                maxList = new LinkedList<>(l);
            }
            return;
        }

        Integer last = null;
        if (!l.isEmpty()) {
            last = l.getLast();
        }
        for (Integer next : map.get(last)) {
            LinkedList<Integer> n = new LinkedList<>(l);
            n.add(next);
            foo(n, month + 1);
        }
    }

    private int fooWithoutList(Integer lastCity, int month) {
        if (month == holidays[0].length) {
            return 0;
        }

        int maxVac = 0;
        for (Integer next : map.get(lastCity)) {
            int vac = holidays[next-1][month] + fooWithoutList(next, month + 1);
            maxVac = Math.max(maxVac, vac);
        }
        return maxVac;
    }

    private int evaluate(List<Integer> l) {
        try {
            int value = 0;
            for (int i = 0; i < l.size(); i++) {
                value += holidays[l.get(i) - 1][i];
            }
            return value;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
