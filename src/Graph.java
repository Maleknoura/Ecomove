import Model.Station;

import java.util.*;

public class Graph {
    private Map<Station, List<Station>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addStation(Station station) {
        adjacencyList.putIfAbsent(station, new ArrayList<>());
    }

    public void addEdge(Station from, Station to) {
        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.putIfAbsent(to, new ArrayList<>());
        adjacencyList.get(from).add(to);
    }

    public List<Station> findShortestPath(Station start, Station end) {
        Map<Station, Station> previous = new HashMap<>();
        Map<Station, Integer> distances = new HashMap<>();
        PriorityQueue<Station> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialisation des distances
        for (Station station : adjacencyList.keySet()) {
            distances.put(station, Integer.MAX_VALUE);
            previous.put(station, null);
        }
        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Station current = queue.poll();
            if (current.equals(end)) break;

            for (Station neighbor : adjacencyList.get(current)) {
                int newDist = distances.get(current) + 1; // Le poids est constant (1) pour chaque arête
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<Station> path = new ArrayList<>();
        for (Station at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public Collection<Station> getAllStations() {
        return adjacencyList.keySet();
    }
}
