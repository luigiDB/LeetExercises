package utils.tree.segmentTree.genericImplementation;

abstract class AbstractNode<T> implements SegmentNode<T> {

    private T value;
    private SegmentNode left;
    private SegmentNode right;

    @Override
    public void init(T value) {
        this.value = value;
    }

    @Override
    public void addLeft(SegmentNode node) {
        left = node;
    }

    @Override
    public void addRight(SegmentNode node) {
        right = node;
    }

    @Override
    public SegmentNode getLeft() {
        return left;
    }

    @Override
    public SegmentNode getRight() {
        return right;
    }

    @Override
    public T get() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
