package utils.tree.segmentTree.genericImplementation;

import java.util.function.Supplier;

public class GenericSegmentTree<T> {

    private final Supplier<SegmentNode> factory;
    private final int size;
    private T[] input;
    private final SegmentNode root;

    public GenericSegmentTree(T[] input, Supplier<SegmentNode> nodeFactory) {
        this.input = input;
        root = nodeFactory.get();
        factory = nodeFactory;
        size = input.length -1;
        build(root, 0, size);
    }

    private SegmentNode build(SegmentNode node, int left, int right) {
        if (left == right)
            node.init(input[left]);
        else {
            node.addLeft(factory.get());
            node.addRight(factory.get());
            int mid = (left + right) / 2;
            SegmentNode l = build(node.getLeft(), left, mid);
            SegmentNode r = build(node.getRight(), mid + 1, right);
            node.init(l, r);
        }
        return node;
    }

    public SegmentNode query(int left, int right) {
        return query(root, left, right, 0, size);
    }

    private SegmentNode query(SegmentNode node, int leftLimit, int rightLimit, int l, int r) {
        if (leftLimit <= l && r <= rightLimit)
            return node;
        SegmentNode accumulator = factory.get();
        int mid = (l + r) / 2;
        if (leftLimit <= mid)
            accumulator.merge(query(node.getLeft(), leftLimit, rightLimit, l, mid));
        if (rightLimit > mid)
            accumulator.merge(query(node.getRight(), leftLimit, rightLimit, mid + 1, r));
        return accumulator;
    }
}
