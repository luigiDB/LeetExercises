package utils;

import java.util.ArrayList;
import java.util.List;

public class Subset {

    public List<List<Integer>> evaluateSubset(int[] set) {
        int n = set.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        // Run a loop for printing all 2^n
        // subsets one by obe
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> currentSubset = new ArrayList<Integer>();
            // Print current subset
            for (int j = 0; j < n; j++) {

                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0) {
                    currentSubset.add(set[j]);
                }
            }
            res.add(currentSubset);
        }
        return res;
    }
}
