package utils.graph.egde.directed;

import utils.graph.egde.IWeightedEdge;

public class WeightedDirectedEdge<T> implements IWeightedEdge<T> {

    private final T nodes;
    private final T nodeF;
    private final int cost;

    public WeightedDirectedEdge(T nodes, T nodeF, int cost) {
        this.nodes = nodes;
        this.nodeF = nodeF;
        this.cost = cost;
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
    public Integer getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        WeightedDirectedEdge<T> that = (WeightedDirectedEdge<T>) o;
        return this.getNodeS().equals(that.getNodeS()) &&
                this.getNodeF().equals(that.getNodeF()) &&
                this.getCost().equals(that.getCost());
    }
}
