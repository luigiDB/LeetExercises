package utils.tree;

public class SegmentTreeSum {

    private final int size;
    private int[] inputArray;
    private final int[] tree;

    public SegmentTreeSum(int[] inputArray) {
        this.inputArray = inputArray;
        size = inputArray.length;
        tree = new int[size * 4];
        build(0, 0, size - 1);
    }

    private void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = inputArray[left];
        } else {
            int middle = (left + right) / 2;
            build(2 * node + 1, left, middle);
            build(2 * node + 2, middle + 1, right);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int query(int leftLimit, int rightLimit) {
        return query(0, 0, size - 1, leftLimit, rightLimit);
    }

    private int query(int node, int left, int right, int leftLimit, int rightLimit) {
        if (leftLimit <= left && right <= rightLimit) {
            return tree[node];
        } else {
            int middle = (left + right) / 2;
            int sum = 0;
            if (leftLimit <= middle)
                sum += query(2 * node + 1, left, middle, leftLimit, rightLimit);
            if (rightLimit >= middle + 1)
                // here the +1 is important otherwise in case of a limit equals to middle a loop is behind the corner
                sum += query(2 * node + 2, middle + 1, right, leftLimit, rightLimit);
            return sum;
        }
    }

    public void update(int position, int value) {
        inputArray[position] = value;
        update(0, 0, size - 1, position, value);
    }

    private void update(int node, int left, int right, int position, int value) {
        if (left == right) {
            tree[node] = value;
        } else {
            int middle = (left + right) / 2;
            if (position <= middle)
                update(2 * node + 1, left, middle, position, value);
            else
                update(2 * node + 2, middle + 1, right, position, value);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}
