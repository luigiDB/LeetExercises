package utils.tree.segmentTree.genericImplementation;

import java.util.function.Supplier;

public class GenericSegmentTree<T> {

    private final Supplier<SegmentNode<T>> factory;
    private final int size;
    private final T[] input;
    private final SegmentNode<T> root;

    public GenericSegmentTree(T[] input, Supplier<SegmentNode<T>> nodeFactory) {
        this.input = input;
        root = nodeFactory.get();
        factory = nodeFactory;
        size = input.length - 1;
        build(root, 0, size);
    }

    private void build(SegmentNode<T> node, int left, int right) {
        if (left == right)
            node.init(input[left]);
        else {
            node.addLeft(factory.get());
            node.addRight(factory.get());
            int mid = (left + right) / 2;
            build(node.getLeft(), left, mid);
            build(node.getRight(), mid + 1, right);
            node.init(node.getLeft(), node.getRight());
        }
    }

    public SegmentNode<T> query(int left, int right) {
        return query(root, left, right, 0, size);
    }

    private SegmentNode<T> query(SegmentNode<T> node, int leftLimit, int rightLimit, int l, int r) {
        if (leftLimit <= l && r <= rightLimit)
            return node;
        SegmentNode<T> accumulator = factory.get();
        int mid = (l + r) / 2;
        if (leftLimit <= mid)
            accumulator.merge(query(node.getLeft(), leftLimit, rightLimit, l, mid));
        if (rightLimit > mid)
            accumulator.merge(query(node.getRight(), leftLimit, rightLimit, mid + 1, r));
        return accumulator;
    }


    public void update(int index, T value) {
        input[index] = value;
        update(root, index, value, 0, size);
    }

    private void update(SegmentNode<T> node, int index, T value, int l, int r) {
        if (l == r)
            node.init(value);
        else {
            int mid = (l + r) / 2;
            if (index <= mid)
                update(node.getLeft(), index, value, l, mid);
            if (index > mid)
                update(node.getRight(), index, value, mid + 1, r);
            node.init(node.getLeft(), node.getRight());
        }
    }
}
