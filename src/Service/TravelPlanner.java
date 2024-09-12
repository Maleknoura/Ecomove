package Service;

import Helpers.DijkstraAlgorithm;
import Helpers.Graph;

import java.util.List;

public class TravelPlanner {
    private Graph graph;

    public TravelPlanner(Graph graph) {
        this.graph = graph;
    }

    public List<String> planItinerary(String start, String end) {
        return DijkstraAlgorithm.findShortestPath(graph, start, end);
    }
}

