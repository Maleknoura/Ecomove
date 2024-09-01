package Service;

import DAO.TicketDAO;
import Model.Ticket;

import java.sql.Connection;
import java.util.UUID;

public class TicketService {
    private TicketDAO ticketDAO;

    public TicketService(Connection connection) {
        this.ticketDAO = new TicketDAO(connection);
    }


    public void createTicket(Ticket ticket) {
        ticketDAO.createTicket(ticket);
    }


    public Ticket getTicketById(UUID id) {
        return ticketDAO.getTicketById(id);
    }
}

