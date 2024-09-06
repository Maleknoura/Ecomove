package main.java.com.wora.DAO;

import main.java.com.wora.Model.Ticket;
import main.java.com.wora.Model.TicketStatus;
import main.java.com.wora.Model.TransportType;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class TicketDAO {
    private Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }


    public void createTicket(Ticket ticket) {
        String query = "INSERT INTO Ticket (id, transportType, purchasePrice, salePrice, saleDate, ticketStatus, contractId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, ticket.getId());
            statement.setObject(2, ticket.getTransportType().toString(), java.sql.Types.OTHER);
            statement.setBigDecimal(3, ticket.getPurchasePrice());
            statement.setBigDecimal(4, ticket.getSalePrice());
            statement.setDate(5, ticket.getSaleDate() != null ? ticket.getSaleDate() : null);

            statement.setObject(6, ticket.getTicketStatus().toString(), java.sql.Types.OTHER);
            statement.setObject(7, ticket.getContractId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void displayAllTickets() {
        String query = "SELECT * FROM Ticket";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UUID ticketId = UUID.fromString(resultSet.getString("id"));
                TransportType transportType = TransportType.valueOf(resultSet.getString("transportType"));
                BigDecimal purchasePrice = resultSet.getBigDecimal("purchasePrice");
                BigDecimal salePrice = resultSet.getBigDecimal("salePrice");
                Date saleDate = resultSet.getDate("saleDate");
                TicketStatus ticketStatus = TicketStatus.valueOf(resultSet.getString("ticketStatus"));
                UUID contractId = UUID.fromString(resultSet.getString("contractId"));

                LocalDate localSaleDate = saleDate != null ? saleDate.toLocalDate() : null;

                System.out.println("Ticket ID: " + ticketId);
                System.out.println("Transport Type: " + transportType);
                System.out.println("Purchase Price: " + purchasePrice);
                System.out.println("Sale Price: " + salePrice);
                System.out.println("Sale Date: " + (localSaleDate != null ? localSaleDate : "N/A"));
                System.out.println("Ticket Status: " + ticketStatus);
                System.out.println("Contract ID: " + contractId);
                System.out.println("---------------------------------");
            }

    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

