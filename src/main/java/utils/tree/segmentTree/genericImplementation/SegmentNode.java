package utils.tree.segmentTree.genericImplementation;

public interface SegmentNode<T> {
    void init(T t);
    //TODO: this init method can defined using the merge function
    void init(SegmentNode<T> l, SegmentNode<T> r);
    void addLeft(SegmentNode<T> node);
    void addRight(SegmentNode<T> node);
    SegmentNode<T> getLeft();
    SegmentNode<T> getRight();
    void merge(SegmentNode<T> node);
    T get();
}
