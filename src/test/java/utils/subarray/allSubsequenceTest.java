package utils.subarray;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class allSubsequenceTest {

    @Test
    public void generate() {
        List<List<Integer>> generate = allSubsequence.generate(new int[]{1, 2, 3, 4});
        List<List<Integer>> expected = new LinkedList<>();
        /**
         * 1
         * 2
         * 1 2
         * 3
         * 1 3
         * 2 3
         * 1 2 3
         * 4
         * 1 4
         * 2 4
         * 1 2 4
         * 3 4
         * 1 3 4
         * 2 3 4
         * 1 2 3 4
         */
    }
}