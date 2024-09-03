package UI;

import DAO.TicketDAO;
import Model.Ticket;
import Model.TicketStatus;
import Model.TransportType;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        System.out.print("Enter sale date (yyyy-MM-dd): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(scanner.nextLine(), formatter);

        // Convert LocalDate to java.sql.Date
        Date saleDate = Date.valueOf(localDate);

        System.out.print("Enter ticket status (SOLD, CANCELLED, PENDING): ");
        TicketStatus ticketStatus = TicketStatus.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter contract ID: ");
        UUID contractId = UUID.fromString(scanner.nextLine());

        Ticket ticket = new Ticket(UUID.randomUUID(), transportType, purchasePrice, salePrice, saleDate, ticketStatus, contractId);

        ticketDAO.createTicket(ticket);
        System.out.println("Ticket created successfully.");
    }

    public void listAllTickets() {
        ticketDAO.displayAllTickets();
    }
    }



