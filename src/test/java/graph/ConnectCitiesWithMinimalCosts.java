package graph;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.undirected.UndirectedGraphConverter;
import utils.graph.egde.undirected.WeightedUndirectedEdge;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/minimum-cost-connect-cities/
 */
public class ConnectCitiesWithMinimalCosts {


    @Test
    public void testConverter() {
        Integer[][] cities = new Integer[][]{{0, 1, 2, 3, 4},
                {1, 0, 5, 0, 7},
                {2, 5, 0, 6, 0},
                {3, 0, 6, 0, 0},
                {4, 7, 0, 0, 0}};

        UndirectedGraphConverter converter = new UndirectedGraphConverter(cities);
        Map<Integer, List<IWeightedEdge<Integer>>> graph = converter.getGraph();

        for (Integer key : graph.keySet()) {
            System.out.println(key);
            System.out.println("\t" + graph.get(key));
        }
    }

    @Test
    public void connectCities() {
        Integer[][] cities = new Integer[][]{{0, 1, 2, 3, 4},
                {1, 0, 5, 0, 7},
                {2, 5, 0, 6, 0},
                {3, 0, 6, 0, 0},
                {4, 7, 0, 0, 0}};

        Queue<IWeightedEdge> queue = new PriorityQueue<>(Comparator.comparingInt(IWeightedEdge::getCost));
        Set<Integer> vertices = new HashSet<>();
        for (int i = 0; i < cities.length; i++) {
            for (int j = 0; j < cities[i].length; j++) {
                if (i == j)
                    continue;
                if (cities[i][j] != 0) {
                    queue.add(new WeightedUndirectedEdge(i, j, cities[i][j]));
                    vertices.add(i);
                    vertices.add(j);
                }
            }
        }
        int totalConnectionCost = 0;
        while(!vertices.isEmpty()) {
            IWeightedEdge nextConnection = queue.remove();
            if(vertices.contains(nextConnection.getNodeS()) || vertices.contains(nextConnection.getNodeF())) {
                vertices.remove(nextConnection.getNodeS());
                vertices.remove(nextConnection.getNodeF());
                totalConnectionCost += nextConnection.getCost();
            }
        }
        System.out.println(totalConnectionCost);
        Assert.assertEquals(10, totalConnectionCost);
    }


}


