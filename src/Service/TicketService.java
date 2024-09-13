package Service;

import DAO.TicketDAO;
import Helpers.DijkstraAlgorithm;
import Helpers.Graph;
import Model.Ticket;


public class TicketService {
    private TicketDAO ticketDAO;
    private Graph graph;
    private DijkstraAlgorithm dijkstraAlgorithm;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }


    public void createTicket(Ticket ticket) {
        ticketDAO.createTicket(ticket);
    }

    public TicketService(TicketDAO ticketDAO, Graph graph) {
        this.ticketDAO = ticketDAO;
        this.graph = graph;
    }

    public void findTicketsByShortestPath() {
        return ;
    }

    public void displayAllTickets() {
        ticketDAO.displayAllTickets();
    }
}

