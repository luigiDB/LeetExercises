package utils.tree.segmentTree;

/**
 * Credit: https://csacademy.com/lesson/segment_trees/
 */
public class SegmentTree {

    private final int size;
    private int[] inputArray;
    private int[] tree;

    public SegmentTree(int[] array) {
        size = array.length;
        inputArray = array;
        tree = new int[4 * size];
        build(0, 1, size - 1);
    }

    private void recalculate(int node) {
        //calculate the solution for the current segment, considering the sons are correctly solved
        tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void build(int node, int left, int right) { //"node" is the index in the array, while "left"
        // and "right" are the ends of the current segment
        if (left == right) {
            tree[node] = inputArray[left]; //we are in a leaf node
        } else {
            int middle = (left + right) / 2;
            build(2 * node + 1, left, middle);
            build(2 * node + 2, middle + 1, right);
            recalculate(node);
        }
    }

    public void update(int pos, int value) {
        inputArray[pos] = value;
        update(0, 0, size - 1, pos, value);
    }

    private void update(int node, int left, int right, int pos, int value) {
        if (left == right) { //we are in the xth leaf
            tree[node] = value;
        } else {
            int middle = (left + right) / 2;
            if (pos <= middle) { //we need to update the left son
                update(2 * node + 1, left, middle, pos, value);
            } else {
                update(2 * node + 2, middle + 1, right, pos, value);
            }
            //after updating said son, recalculate the current segment
            recalculate(node);
        }
    }

    public int query(int l, int r) {
        return query(0, 0, size-1, l, r);
    }

    private int query(int node, int left, int right, int leftLimit, int rightLimit) {
        if (leftLimit <= left && right <= rightLimit) {
            //the segment of "node" is completely included in the query
            return tree[node];
        } else {
            int answer = Integer.MIN_VALUE;
            int middle = (left + right) / 2;
            if (leftLimit <= middle) {
                //the query segment and the left son segment have at least one element in common
                answer = Math.max(answer, query(2 * node + 1, left, middle, leftLimit, rightLimit));
            }
            if (rightLimit >= middle + 1) {
                //the query segment and the right son segment have at least one element in common
                answer = Math.max(answer, query(2 * node + 2, middle + 1, right, leftLimit, rightLimit));
            }
            //we would not have entered this function if (leftLimit, rightLimit) and (left, right) had nothing in common,
            //so there is no risk of answer returning -Infinity here, as either the left or the right son
            //would update it
            return answer;
        }
    }
}
