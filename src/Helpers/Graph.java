package Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public void loadFromDatabase(Connection connection) {


        String query = "SELECT departureLocation, arrivalLocation, distance FROM Station";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String departure = resultSet.getString("departureLocation");
                String arrival = resultSet.getString("arrivalLocation");
                double distance = resultSet.getDouble("distance");

                adjacencyList.putIfAbsent(departure, new ArrayList<>());
                adjacencyList.putIfAbsent(arrival, new ArrayList<>());

                adjacencyList.get(departure).add(new Edge(arrival, distance));
                adjacencyList.get(arrival).add(new Edge(departure, distance));
            }



        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public static class Edge {
        private final String destination;
        private final double distance;

        public Edge(String destination, double distance) {
            this.destination = destination;
            this.distance = distance;
        }

        public String getDestination() {
            return destination;
        }

        public double getDistance() {
            return distance;
        }
    }
}
