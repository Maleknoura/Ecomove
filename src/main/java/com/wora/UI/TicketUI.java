package main.java.com.wora.UI;

import main.java.com.wora.Model.Ticket;
import main.java.com.wora.Model.TicketStatus;
import main.java.com.wora.Model.TransportType;
import main.java.com.wora.Service.TicketService;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class TicketUI {
    private TicketService ticketService;
    private Scanner scanner;

    public TicketUI(TicketService ticketService) {
        this.ticketService = ticketService; // Correction de la référence
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

        Date saleDate = Date.valueOf(localDate);

        System.out.print("Enter ticket status (SOLD, CANCELLED, PENDING): ");
        TicketStatus ticketStatus = TicketStatus.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter contract ID: ");
        UUID contractId = UUID.fromString(scanner.nextLine());

        Ticket ticket = new Ticket(UUID.randomUUID(), transportType, purchasePrice, salePrice, saleDate, ticketStatus, contractId);

        ticketService.createTicket(ticket);
        System.out.println("Ticket created successfully.");
    }

    public void listAllTickets() {
        ticketService.displayAllTickets();
    }

    }

