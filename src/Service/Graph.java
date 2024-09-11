package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void loadFromDatabase(Connection connection) throws SQLException {
        String query = "SELECT departureLocation, arrivalLocation, distance FROM Station";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String departure = resultSet.getString("departureLocation");
                String arrival = resultSet.getString("arrivalLocation");
                double distance = resultSet.getDouble("distance");

                adjacencyList.putIfAbsent(departure, new ArrayList<>());
                adjacencyList.putIfAbsent(arrival, new ArrayList<>());

                adjacencyList.get(departure).add(new Edge(arrival, distance));
                adjacencyList.get(arrival).add(new Edge(departure, distance));
            }
        }
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public static class Edge {
        String destination;
        double distance;

        Edge(String destination, double distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }
}
