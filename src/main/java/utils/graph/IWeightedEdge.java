package utils.graph;

public interface IWeightedEdge<T> {
    T getNodeS();
    T getNodeF();
    int getCost();
}
