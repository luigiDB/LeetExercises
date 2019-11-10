package leetfree;

import java.util.HashSet;
import java.util.Set;

/*
On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
What is the largest possible number of moves we can make?
Example 1:
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:
Input: stones = [[0,0]]
Output: 0
Note:
1 <= stones.length <= 1000
0 <= stones[i][j] < 10000
 */
public class __947_MostStonesRemovedWithSameRowOrColumn {
    /**
     * possible solution is to recursively delete nodes
     * Pseudocode based on the fact that exist a map that classify each node per row and one per column
     * int foo(int moves, visited, point P) {
     *     nexts.addAll(rowMap.get(P.row)
     *     nexts.addAll(columnMap.get(P.column)
     *     nexts.removeAll(visited)
     *     if(nexts.isEmpty) return moves;
     *     int max = Integers.MIN;
     *     for (point p2: nexts) {
     *          visited.add(P)
     *          max = Math.max(max, foo(moves+1, visited, p2))
     *          visited.remove(P)
     *     }
     *     return max;
     * }
     */
    /**
     * can be resolved with union find method at the end "N - number of groups" is the number of elements that is
     * possible to delete
     */
    class Solution {
        public int removeStones(int[][] stones) {
            int N = stones.length;
            DSU dsu = new DSU(20000);

            for (int[] stone : stones)
                dsu.union(stone[0], stone[1] + 10000);

            Set<Integer> seen = new HashSet();
            for (int[] stone : stones)
                seen.add(dsu.find(stone[0]));

            return N - seen.size();
        }
    }

    class DSU {
        int[] parent;

        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; ++i)
                parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}
