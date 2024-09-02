package UI;

import DAO.TicketDAO;
import Model.Ticket;
import Model.TicketStatus;
import Model.TransportType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class TicketUI {
    private TicketDAO ticketDAO;
    private Scanner scanner;

    public TicketUI(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
        this.scanner = new Scanner(System.in);
    }

    public void createTicket() {
        System.out.print("Enter transport type (PLANE, TRAIN, BUS): ");
        TransportType transportType = TransportType.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter purchase price: ");
        BigDecimal purchasePrice = scanner.nextBigDecimal();

        System.out.print("Enter sale price: ");
        BigDecimal salePrice = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Enter sale date (yyyy-MM-dd ): ");
        LocalDateTime saleDate = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Enter ticket status (SOLD, CANCELLED, PENDING): ");
        TicketStatus ticketStatus = TicketStatus.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter contract ID: ");
        UUID contractId = UUID.fromString(scanner.nextLine());

        Ticket ticket = new Ticket(UUID.randomUUID(), transportType, purchasePrice, salePrice, saleDate, ticketStatus, contractId);

        ticketDAO.createTicket(ticket);
        System.out.println("Ticket created successfully.");
    }

    public void displayTicket() {
        System.out.print("Enter ticket ID: ");
        UUID ticketId = UUID.fromString(scanner.nextLine());

        Ticket ticket = ticketDAO.getTicketById(ticketId);
        if (ticket != null) {
            System.out.println("Ticket ID: " + ticket.getId());
            System.out.println("Transport Type: " + ticket.getTransportType());
            System.out.println("Purchase Price: " + ticket.getPurchasePrice());
            System.out.println("Sale Price: " + ticket.getSalePrice());
            System.out.println("Sale Date: " + ticket.getSaleDate());
            System.out.println("Ticket Status: " + ticket.getTicketStatus());
            System.out.println("Contract ID: " + ticket.getContractId());
        } else {
            System.out.println("Ticket not found.");
        }
    }


}
