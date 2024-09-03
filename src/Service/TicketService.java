package Service;

import DAO.TicketDAO;
import Model.Ticket;

import java.sql.Connection;


public class TicketService {
    private TicketDAO ticketDAO;

    public TicketService(Connection connection) {
        this.ticketDAO = new TicketDAO(connection);
    }


    public void createTicket(Ticket ticket) {
        ticketDAO.createTicket(ticket);
    }


    public void displayAllTickets() {
        ticketDAO.displayAllTickets();
    }
}

