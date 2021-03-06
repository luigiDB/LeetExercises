package leetfree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Possible solution
 * https://www.cnblogs.com/lightwindy/p/9739124.html
 */
/*
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for
$10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means
person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the
person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
Note:
A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:
Input:
[[0,1,10], [2,0,5]]
Output:
2
Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.
Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:
Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
Output:
1
Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.
Therefore, person #1 only need to give person #0 $4, and all debt is settled.
*/
public class _465_OptimalAccountBalancing {

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> debt = new HashMap<>();
        for (int[] a : transactions) {
            debt.put(a[0], debt.getOrDefault(a[0], 0) + a[2]);
            debt.put(a[1], debt.getOrDefault(a[1], 0) - a[2]);
        }

        return dfs(0, new ArrayList<>(debt.values()));
    }

    private int dfs(int start, List<Integer> debt) {
        while (start < debt.size() && debt.get(start) == 0)
            start++;
        if (start == debt.size()) return 0;
        int cnt = Integer.MAX_VALUE;
        for (int i = start + 1; i < debt.size(); i++) {
            if (debt.get(i) * debt.get(start) < 0) {
                debt.set(i, debt.get(i) + debt.get(start));
                cnt = Math.min(cnt, 1 + dfs(start + 1, debt));
                debt.set(i, debt.get(i) - debt.get(start));
            }
        }
        return cnt;
    }
}
