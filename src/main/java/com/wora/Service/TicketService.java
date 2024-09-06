package main.java.com.wora.Service;

import main.java.com.wora.DAO.TicketDAO;
import main.java.com.wora.Model.Ticket;


public class TicketService {
    private TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }


    public void createTicket(Ticket ticket) {
        ticketDAO.createTicket(ticket);
    }



    public void displayAllTickets() {
        ticketDAO.displayAllTickets();
    }
}

