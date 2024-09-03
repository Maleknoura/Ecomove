package DAO;

import Model.Ticket;
import Model.TicketStatus;
import Model.TransportType;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
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
                String transportType = resultSet.getString("transportType");
                BigDecimal purchasePrice = resultSet.getBigDecimal("purchasePrice");
                BigDecimal salePrice = resultSet.getBigDecimal("salePrice");
                LocalDateTime saleDate = resultSet.getTimestamp("saleDate").toLocalDateTime();
                String ticketStatus = resultSet.getString("ticketStatus");
                UUID contractId = UUID.fromString(resultSet.getString("contractId"));

                System.out.println("Ticket ID: " + ticketId);
                System.out.println("Transport Type: " + transportType);
                System.out.println("Purchase Price: " + purchasePrice);
                System.out.println("Sale Price: " + salePrice);
                System.out.println("Sale Date: " + saleDate);
                System.out.println("Ticket Status: " + ticketStatus);
                System.out.println("Contract ID: " + contractId);
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

