import DAO.*;
import DataBase.DbFunction;
import Helpers.Graph;
import Service.ClientService;
import Service.ContractService;
import Service.TicketService;
import Service.TravelPlanner;

import Service.TravelPlanner;
import UI.*;


import java.sql.Connection;

public class Main {
    public static void main(String[] args) {


        DbFunction db = DbFunction.getInstance();
        Connection conn = db.connectToDb("Ecomove", "postgres", "administrateur");
        PartnerDAO partnerDAO = new PartnerDAO(conn);
        ContractDAO contractDAO = new ContractDAO(conn);
        PromotionalOfferDAO promotionalOfferDAO = new PromotionalOfferDAO(conn);

        TicketDAO ticketDAO = new TicketDAO(conn);
        Graph graph=new Graph();
        TravelPlanner travelPlanner = new TravelPlanner(graph);

        ContractService contractService = new ContractService(contractDAO);
        TicketService ticketService = new TicketService(ticketDAO);


        PartnerUI partnerUI = new PartnerUI(partnerDAO);
        ContractUI contractUI = new ContractUI(contractService);
        PromotionalOfferUI promotionalOfferUI = new PromotionalOfferUI(promotionalOfferDAO);
        TicketUI ticketUI = new TicketUI(ticketService);

        ConsoleUI consoleUI = new ConsoleUI(conn);
        ClientDAO clientDAO = new ClientDAO(conn);
        ClientService clientService = new ClientService(clientDAO);
        ClientUI clientUI = new ClientUI(clientService,travelPlanner);


        //consoleUI.showMainMenu();
        graph.loadFromDatabase(conn);

        clientUI.start();
    }

    }



