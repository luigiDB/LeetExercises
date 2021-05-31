package facebook;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given a tree that contains N nodes, each containing an integer u which corresponds to a lowercase
 * character c in the string s using 1-based indexing.
 * You are required to answer Q queries of type [u, c], where u is an integer and c is a lowercase letter. The query
 * result is the number of nodes in the subtree of node u containing c.
 */
public class NodesInASubtree {

    @Test
    public void a() {
        String s_1 = "aba";

        Node root_1 = new Node(1);
        root_1.children.add(new Node(2));
        root_1.children.add(new Node(3));
        ArrayList<Query> queries_1 = new ArrayList<>();

        queries_1.add(new Query(1, 'a'));
        Assert.assertArrayEquals(new int[]{2}, countOfNodes(root_1, queries_1, s_1));
    }

    @Test
    public void b() {
        String s_2 = "abaacab";

        Node root_2 = new Node(1);
        root_2.children.add(new Node(2));
        root_2.children.add(new Node(3));
        root_2.children.add(new Node(7));
        root_2.children.get(0).children.add(new Node(4));
        root_2.children.get(0).children.add(new Node(5));
        root_2.children.get(1).children.add(new Node(6));

        ArrayList<Query> queries_2 = new ArrayList<>();
        queries_2.add(new Query(1, 'a'));
        queries_2.add(new Query(2, 'b'));
        queries_2.add(new Query(3, 'a'));

        Assert.assertArrayEquals(new int[]{4, 1, 2}, countOfNodes(root_2, queries_2, s_2));
    }

    // Add any helper functions you may need here
    private Node find(Node root, int num) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            Node poll = q.poll();
            if(poll.val == num)
                return poll;
            q.addAll(poll.children);
        }

        return null;
    }

    private int count(Node root, String s, char target) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int counter = 0;
        while(!q.isEmpty()) {
            Node poll = q.poll();

            if(s.charAt(poll.val-1)==target)
                counter++;
            q.addAll(poll.children);
        }

        return counter;
    }



    int[] countOfNodes(Node root, ArrayList<Query> queries, String s) {
        // Write your code here

        int[] res = new int[queries.size()];

        for(int i = 0; i < queries.size(); i++) {
            Query query = queries.get(i);

            Node start = find(root, query.u);

            res[i] = count(start, s, query.c);
        }

        return res;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
            val = 0;
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Query {
        int u;
        char c;
        Query(int u, char c) {
            this.u = u;
            this.c = c;
        }
    }

}