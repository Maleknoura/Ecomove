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
            statement.setString(2, ticket.getTransportType().name());
            statement.setBigDecimal(3, ticket.getPurchasePrice());
            statement.setBigDecimal(4, ticket.getSalePrice());
            statement.setTimestamp(5, Timestamp.valueOf(ticket.getSaleDate()));  // Utilisez Timestamp ici
            statement.setString(6, ticket.getTicketStatus().name());
            statement.setObject(7, ticket.getContractId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Ticket getTicketById(UUID id) {
        String query = "SELECT * FROM Ticket WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UUID ticketId = UUID.fromString(resultSet.getString("id"));
                TransportType transportType = TransportType.valueOf(resultSet.getString("transportType"));
                BigDecimal purchasePrice = resultSet.getBigDecimal("purchasePrice");
                BigDecimal salePrice = resultSet.getBigDecimal("salePrice");
                Timestamp saleDateTimestamp = resultSet.getTimestamp("saleDate");
                LocalDateTime saleDate = saleDateTimestamp.toLocalDateTime();
                TicketStatus ticketStatus = TicketStatus.valueOf(resultSet.getString("ticketStatus"));
                UUID contractId = (UUID) resultSet.getObject("contractId");

                return new Ticket(ticketId, transportType, purchasePrice, salePrice, saleDate, ticketStatus, contractId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

