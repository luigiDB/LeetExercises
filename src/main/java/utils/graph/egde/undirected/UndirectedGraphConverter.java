package utils.graph.egde.undirected;

import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.undirected.WeightedUndirectedEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndirectedGraphConverter {

    private Map<Integer, List<IWeightedEdge<Integer>>> graph;
    private Integer[][] matrix;

    public UndirectedGraphConverter(Integer[][] matrix) {
        this.matrix = matrix;
    }

    public Map<Integer, List<IWeightedEdge<Integer>>> getGraph() {
        if (graph == null)
            initGraph();
        return graph;
    }

    private void initGraph() {
        Map<Integer, List<IWeightedEdge<Integer>>> m = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            List<IWeightedEdge<Integer>> currentNode = new ArrayList<>();
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j)
                    continue;
                if (matrix[i][j] != 0)
                    currentNode.add(new WeightedUndirectedEdge<>(i, j, matrix[i][j]));
            }
            m.put(i, currentNode);
        }
        graph = m;
    }

    public Integer[][] getMatrix() {
        return matrix;
    }
}
