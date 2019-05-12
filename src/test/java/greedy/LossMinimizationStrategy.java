package greedy;

import org.junit.Before;
import org.junit.Test;
import utils.Permutation;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/job-selection-problem-loss-minimization-strategy-set-2/
 */
public class LossMinimizationStrategy {

    private Permutation perm;

    @Before
    public void setUp() {
        perm = new Permutation();
    }

    @Test
    public void exaustiveSearch() {

    }


    @Test
    public void greedySolution() {
        Queue<Integer> heap = new PriorityQueue<Integer>();
        heap.addAll(Arrays.asList(4,2,151,15,1,52,12));
        int lossRate = 10;
    }
}
