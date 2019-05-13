package utils.graph.egde;

public interface IWeightedEdge<T> {
    T getNodeS();
    T getNodeF();
    int getCost();
}
