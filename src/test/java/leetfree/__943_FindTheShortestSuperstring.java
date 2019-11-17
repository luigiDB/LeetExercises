package leetfree;

/*
Given an array A of strings, find any smallest string that contains each string in A as a substring.
We may assume that no string in A is substring of another string in A.
Example 1:
Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:
Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"
Note:
1 <= A.length <= 12
1 <= A[i].length <= 20
 */
public class __943_FindTheShortestSuperstring {
    /**
     * In practise we want to find the most costly hamiltonian path.
     * There isn't a closed algorithm to obtain it so we need to search for the best solution.
     * We need to build all the directed edges of the graph with cost the number of superimposed characters.
     * for each node:
     *      foo(node, visited, StringBuilder)
     *
     * where
     * foo(node, visited, Stringbuilder)
     *      List<Pair<SUPERIMPOSEDCOUNT, NODES>> possibleNextSteps = ***look after***
     *      for each possibleNextStep:
     *          foo(    possibleNextStep.secondNode,
     *                  visited + possibleNextStep.secondNode,
     *                  Stringbuilder.add(possibleNextStep.secondNode.substring(SUPERIMPOSEDCOUNT)))
     *
     * ***
     * In practise the next step can be any reachable node with the maximum number of superimposed characters and in
     * case there are multiple with maximums superimposed characters both path must be followed (for each on line 31).
     *
     * A way to easily get the list of next steps is to create a structure like this one
     * HashMap< String,                                     -
     *          TreeMap<    Integer,                        -   TreeMap that keep the edge list for each count of
     *                                                      -   superimposed characters (with treeMap we can search key in order )
     *                      List<Pair<String, String>>      -   Pair of star and end node for each edge. Can we also keep only the end node.
     *                 >
     *        >
     *  In any case we should always consider the visited nodes so we cannot precompute the list of steps for each node.
     */

    /**
     * TO BE VERIFIED:
     * We can also iteratively proceed by deletion. For each merge delete now unless edges :
     *      1_ exit nodes from the node
     *      2_ entering edges to possibleNextStep
     *      3_ if present the edge from possibleNextStep to node
     * and continue build like Mst (Maximum spanning tree)
     */
}
