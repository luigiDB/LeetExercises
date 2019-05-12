package utils.graph;

import java.util.Objects;

public class UnweightedDirectedEdge<T> implements IEdge<T> {

    private final T nodes;
    private final T nodeF;

    public UnweightedDirectedEdge(T nodes, T nodeF) {
        this.nodes = nodes;
        this.nodeF = nodeF;
    }

    @Override
    public T getNodeS() {
        return nodes;
    }

    @Override
    public T getNodeF() {
        return nodeF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnweightedDirectedEdge<?> that = (UnweightedDirectedEdge<?>) o;
        return nodes.equals(that.nodes) &&
                nodeF.equals(that.nodeF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, nodeF);
    }
}
