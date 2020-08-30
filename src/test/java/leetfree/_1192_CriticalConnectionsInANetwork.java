package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where
connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly
 or indirectly through the network.
A critical connection is a connection that, if removed, will make some server unable to reach some other server.
Return all critical connections in the network in any order.
Example 1:
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
 */

/**
 * Tarjan's Algorithm
 */
public class _1192_CriticalConnectionsInANetwork {

    @Test
    public void givenTest() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(List.of(0,1));
        input.add(List.of(1,2));
        input.add(List.of(2,0));
        input.add(List.of(1,3));
        List<List<Integer>> output = criticalConnections(4, input);
        Assert.assertTrue(output.contains(List.of(1,3)));
    }

    ArrayList<Integer>[] graph;
    int[] visitedTime;
    int[] lowTime;
    int time;
    List<List<Integer>> result;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        result = new ArrayList<>();
        graph = new ArrayList[n];
        visitedTime = new int[n];
        lowTime = new int[n];
        time = 0;
        generateGraph(connections);
        boolean[] visited = new boolean[n];
        dfs(0, -1, visited);
        return result;
    }

    private void dfs(int current, int parent, boolean[] visited) {
        visited[current] = true;
        visitedTime[current] = lowTime[current] = time++;
        for (int neighbor : graph[current]) {
            if (neighbor == parent) continue;
            if (!visited[neighbor]) {
                dfs(neighbor, current, visited);
                lowTime[current] = Math.min(lowTime[current], lowTime[neighbor]);
                if (lowTime[neighbor] > visitedTime[current]) {
                    result.add(Arrays.asList(current, neighbor));
                }
            } else {
                lowTime[current] = Math.min(lowTime[current], visitedTime[neighbor]);
            }
        }
    }

    private void generateGraph(List<List<Integer>> connections) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> list : connections) {
            graph[list.get(0)].add(list.get(1));
            graph[list.get(1)].add(list.get(0));
        }
    }
}
