package utils.tree.segmentTree.genericImplementation;

abstract class AbstractNode<T> implements SegmentNode<T> {

    private T value;
    private SegmentNode<T> left;
    private SegmentNode<T> right;

    @Override
    public void init(T value) {
        this.value = value;
    }

    @Override
    public void addLeft(SegmentNode<T> node) {
        left = node;
    }

    @Override
    public void addRight(SegmentNode<T> node) {
        right = node;
    }

    @Override
    public SegmentNode<T> getLeft() {
        return left;
    }

    @Override
    public SegmentNode<T> getRight() {
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
