package UI;

import DAO.TicketDAO;
import Model.*;
import Service.TicketService;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class TicketUI {
    private TicketService ticketService;
    private Scanner scanner;

    public TicketUI(TicketService ticketService) {
        this.ticketService = ticketService;
        this.scanner = new Scanner(System.in);
    }

    public void createTicket() {
        try {
            System.out.print("Enter transport type (PLANE, TRAIN, BUS): ");
            TransportType transportType = TransportType.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Enter purchase price: ");
            BigDecimal purchasePrice = scanner.nextBigDecimal();

            System.out.print("Enter sale price: ");
            BigDecimal salePrice = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.print("Enter sale date (yyyy-MM-dd HH:mm:ss): ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime saleDate = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.print("Enter ticket status (SOLD, CANCELLED, PENDING): ");
            TicketStatus ticketStatus = TicketStatus.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Enter contract ID: ");
            UUID contractId = UUID.fromString(scanner.nextLine());

            System.out.print("Enter departure date and time (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime departureDateTime = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.print("Enter arrival date and time (yyyy-MM-dd HH:mm:ss): ");
            LocalDateTime arrivalDateTime = LocalDateTime.parse(scanner.nextLine(), formatter);


            Station station = null;

            Ticket ticket = new Ticket(
                    UUID.randomUUID(),
                    transportType,
                    purchasePrice,
                    salePrice,
                    saleDate,
                    ticketStatus,
                    contractId,
                    departureDateTime,
                    arrivalDateTime,
                    station
            );

            ticketService.createTicket(ticket);
            System.out.println("Ticket created successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid format for price or date.");
            scanner.next();
        }
    }


    public void listAllTickets() {
        ticketService.displayAllTickets();
    }

    }

