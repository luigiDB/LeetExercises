package utils.graph;

import java.util.Objects;

public class WeightedUndirectedEdge<T> implements IWeightedEdge<T> {

    private final T nodeS;
    private final T nodeF;
    private final int cost;

    public WeightedUndirectedEdge(T nodeS, T nodeF, int cost) {
        this.nodeS = nodeS;
        this.nodeF = nodeF;
        this.cost = cost;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedUndirectedEdge<?> edge = (WeightedUndirectedEdge<?>) o;
        boolean partialTruth = verifyCommonNodes(o);
        return cost == edge.cost && partialTruth;
    }

    private boolean verifyCommonNodes(Object o) {
        if(this.getNodeS().equals(((IWeightedEdge<?>) o).getNodeS())) {
            if(this.getNodeF().equals(((WeightedUndirectedEdge<?>) o).nodeF)) {
                return true;
            }
        }
        if(this.getNodeS().equals(((IWeightedEdge<?>) o).getNodeF())) {
            if(this.getNodeF().equals(((WeightedUndirectedEdge<?>) o).nodeS)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeS, nodeF, cost);
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
    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "WeightedUndirectedEdge{" + nodeS +", " + nodeF +", cost=" + cost +'}';
    }
}
