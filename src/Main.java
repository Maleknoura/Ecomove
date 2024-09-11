import DAO.ContractDAO;
import DAO.PartnerDAO;
import DAO.PromotionalOfferDAO;
import DAO.TicketDAO;
import DataBase.DbFunction;
import Service.ContractService;
import Service.Graph;
import Service.TicketService;
import Service.TravelPlanner;
import UI.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            DbFunction db = DbFunction.getInstance();
            conn = db.connectToDb("Ecomove", "postgres", "administrateur");

            PartnerDAO partnerDAO = new PartnerDAO(conn);
            ContractDAO contractDAO = new ContractDAO(conn);
            PromotionalOfferDAO promotionalOfferDAO = new PromotionalOfferDAO(conn);
            TicketDAO ticketDAO = new TicketDAO(conn);

            ContractService contractService = new ContractService(contractDAO);
            TicketService ticketService = new TicketService(ticketDAO);

            PartnerUI partnerUI = new PartnerUI(partnerDAO);
            ContractUI contractUI = new ContractUI(contractService);
            PromotionalOfferUI promotionalOfferUI = new PromotionalOfferUI(promotionalOfferDAO);
            TicketUI ticketUI = new TicketUI(ticketService);

            ConsoleUI consoleUI = new ConsoleUI(conn);

            Graph graph = new Graph();
            graph.loadFromDatabase(conn);
            TravelPlanner travelPlanner = new TravelPlanner(graph);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter start city: ");
            String start = scanner.nextLine();
            System.out.print("Enter end city: ");
            String end = scanner.nextLine();

            List<String> path = travelPlanner.planItinerary(start, end);
            System.out.println("Shortest path from " + start + " to " + end + ": " + path);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
