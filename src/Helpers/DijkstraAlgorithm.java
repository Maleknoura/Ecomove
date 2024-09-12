package Helpers;

import java.util.*;

public class DijkstraAlgorithm {

    public static List<String> findShortestPath(Graph graph, String start, String end) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null.");
        }
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end nodes cannot be null.");
        }

        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Set<String> visited = new HashSet<>();

        for (String node : graph.getAdjacencyList().keySet()) {
            distances.put(node, Double.MAX_VALUE);
            previous.put(node, null);
        }

        if (!graph.getAdjacencyList().containsKey(start) || !graph.getAdjacencyList().containsKey(end)) {
            System.out.println("Invalid start or end city.");
            return Collections.emptyList();
        }

        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            if (current.equals(end)) break;

            List<Graph.Edge> edges = graph.getAdjacencyList().get(current);
            if (edges == null) continue;

            for (Graph.Edge edge : edges) {
                if (visited.contains(edge.getDestination())) {
                    continue;
                }

                double newDist = distances.get(current) + edge.getDistance();
                if (newDist < distances.get(edge.getDestination())) {
                    distances.put(edge.getDestination(), newDist);
                    previous.put(edge.getDestination(), current);
                    queue.add(edge.getDestination());
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path.size() == 1 ? Collections.emptyList() : path;
    }
}
