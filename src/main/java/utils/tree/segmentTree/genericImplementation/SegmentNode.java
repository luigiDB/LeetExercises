package utils.tree.segmentTree.genericImplementation;

public interface SegmentNode<T> {
    void init(T t);
    void init(SegmentNode<T> l, SegmentNode<T> r);
    void addLeft(SegmentNode<T> node);
    void addRight(SegmentNode<T> node);
    SegmentNode<T> getLeft();
    SegmentNode<T> getRight();
    void merge(SegmentNode<T> node);
    T get();
}
