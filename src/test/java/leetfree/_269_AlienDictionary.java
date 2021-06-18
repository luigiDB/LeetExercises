package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of
this new language. Derive the order of letters in this language.
Example 1:
Given the following words in dictionary,
["wrt","wrf","er","ett","rftt"]
The correct order is: "wertf".
Example 2:
Given the following words in dictionary,
["z","x"]
The correct order is: "zx".
Example 3:
Given the following words in dictionary,
["z","x","z"]
The order is invalid, so return "".
Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */
public class _269_AlienDictionary {

    @Test
    public void baseCase() {
        Assert.assertEquals("zx", alienDictionary(Arrays.asList("z", "x")));
    }

    @Test
    public void impossibleCase() {
        Assert.assertEquals("", alienDictionary(Arrays.asList("z", "x", "z")));
    }

    @Test
    public void realisticCase() {
        Assert.assertEquals("wertf", alienDictionary(Arrays.asList("wrt", "wrf", "er", "ett", "rftt")));
    }

    private String alienDictionary(List<String> dict) {
        Node root = buildTree(dict);
        Set<Pair> dependencies = extractDep(root);
        return sortDep(dependencies);
    }

    /**
     * Use DAG Topological Sort
     *
     * @param dependencies list of edges
     * @return the topological sort if it's unique
     */
    private String sortDep(Set<Pair> dependencies) {
        //create graph
        Map<Character, Integer> fanIn = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();
        Set<Character> nodes = new HashSet<>();

        for (Pair p : dependencies) {
            nodes.add(p.start);
            nodes.add(p.end);
        }

        for (Character node : nodes) {
            fanIn.put(node, 0);
            graph.put(node, new LinkedList<>());
        }

        for (Pair edge : dependencies) {
            fanIn.put(edge.end, fanIn.get(edge.end) + 1);
            graph.get(edge.start).add(0, edge.end);
        }

        //topological sort
        StringBuilder sb = new StringBuilder();
        while (!fanIn.isEmpty()) {
            Character nextChoice = null;
            int matches = 0;
            for (Map.Entry<Character, Integer> entry : fanIn.entrySet()) {
                if (entry.getValue() == 0) {
                    nextChoice = entry.getKey();
                    matches++;
                }
            }
            if (matches > 1 || matches == 0)
                return "";

            //clean graph
            if (graph.get(nextChoice) != null)
                for (Character nextNode : graph.get(nextChoice)) {
                    fanIn.put(nextNode, fanIn.get(nextNode) - 1);
                }
            fanIn.remove(nextChoice);
            graph.remove(nextChoice);

            sb.append(nextChoice);
        }
        return sb.toString();
    }

    private Set<Pair> extractDep(Node root) {
        Set<Pair> edgeList = new HashSet<>();
        treeVisit(root, edgeList);

        return edgeList;
    }

    private void treeVisit(Node root, Set<Pair> edgeList) {
        edgeList.addAll(root.returnEdges());
        for (int i = 0; i < root.sons.length; i++) {
            if (root.sons[i] != null) {
                treeVisit(root.sons[i], edgeList);
            }
        }
    }

    private Node buildTree(List<String> dict) {
        Node root = new Node();

        for (String elem : dict) {
            Node lastVisited = root;
            for (int i = 0; i < elem.length(); i++) {
                char letter = elem.charAt(i);
                lastVisited = lastVisited.addDependencyAndReturnSon(letter);
            }
        }

        return root;
    }

    class Pair {
        char start;
        char end;

        public Pair(char start, char end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pair) {
                return start == ((Pair) o).start && end == ((Pair) o).end;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return start * 256 + end;
        }
    }

    class Node {
        LinkedList<Character> siblings = new LinkedList<>();
        Node[] sons = new Node[256];

        public Node addDependencyAndReturnSon(char newSon) {
            siblings.addLast(newSon);
            if (sons[newSon] == null) {
                sons[newSon] = new Node();
            }
            return sons[newSon];
        }

        public List<Pair> returnEdges() {
            List<Pair> edges = new LinkedList<>();
            if (siblings.size() == 1) {
                return edges;
            }
            for (int i = 0; i < siblings.size() - 1; i++) {
                if (siblings.get(i) != siblings.get(i + 1))
                    edges.add(new Pair(siblings.get(i), siblings.get(i + 1)));
            }
            return edges;
        }
    }
}
