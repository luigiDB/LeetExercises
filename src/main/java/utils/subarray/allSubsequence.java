package utils.subarray;

import java.util.LinkedList;
import java.util.List;

public class allSubsequence {

    static public List<List<Integer>> generate(int[] input) {
        List<List<Integer>> res = new LinkedList<>();

        for (int i = 0; i < (1<<input.length); i++) {
            List<Integer> n = new LinkedList<>();
            for (int j = 0; j < input.length; j++) {
                if ((i & (1 << j)) > 0) {
                    n.add(input[j]);
                }
            }
            res.add(n);
        }

        return res;
    }
}
