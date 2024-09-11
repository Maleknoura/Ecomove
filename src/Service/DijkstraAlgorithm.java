package Service;

import java.util.*;


public class DijkstraAlgorithm {

    public static List<String> findShortestPath(Graph graph, String start, String end) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

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
            if (current.equals(end)) break;

            List<Graph.Edge> edges = graph.getAdjacencyList().get(current);
            if (edges == null) continue;

            for (Graph.Edge edge : edges) {
                double newDist = distances.get(current) + edge.distance;
                if (newDist < distances.get(edge.destination)) {
                    distances.put(edge.destination, newDist);
                    previous.put(edge.destination, current);
                    queue.add(edge.destination);
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

