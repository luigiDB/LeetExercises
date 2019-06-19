package utils.graph.egde.directed;

import utils.graph.egde.IEdge;

import java.util.Objects;

public class UnweightedDirectedEdge<T> implements IEdge<T> {

    private final T nodeS;
    private final T nodeF;

    public UnweightedDirectedEdge(T nodeS, T nodeF) {
        this.nodeS = nodeS;
        this.nodeF = nodeF;
    }

    public static UnweightedDirectedEdge createEdge(char nodeS, char nodeF) {
        return new UnweightedDirectedEdge(nodeS, nodeF);
    }

    public static UnweightedDirectedEdge createEdge(int nodeS, int nodeF) {
        return new UnweightedDirectedEdge(nodeS, nodeF);
    }

    @Override
    public T getNodeS() {
        return nodeS;
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
        return nodeS.equals(that.nodeS) &&
                nodeF.equals(that.nodeF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeS, nodeF);
    }
}
