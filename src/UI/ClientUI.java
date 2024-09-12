package UI;

import Helpers.DijkstraAlgorithm;
import Helpers.Graph;
import DataBase.DbFunction;

import java.util.List;
import java.util.Scanner;

public class ClientUI {

    private Graph graph;

    public ClientUI(Graph graph) {
        this.graph = graph;
    }

    public void clientMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the start city: ");
        String startCity = scanner.nextLine();
        System.out.println("Enter the end city: ");
        String endCity = scanner.nextLine();

        List<String> path = DijkstraAlgorithm.findShortestPath(graph, startCity, endCity);

        if (path.isEmpty()) {
            System.out.println("No path found or invalid cities.");
        } else {
            System.out.println("Shortest path from " + startCity + " to " + endCity + ":");
            for (String city : path) {
                System.out.println(city);
            }
        }
    }
}
