package DAO;

import Model.Station;
import Model.Ticket;
import Model.TicketStatus;
import Model.TransportType;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketDAO {
    private Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTicket(Ticket ticket) {
        String query = "INSERT INTO Ticket (id, transportType, purchasePrice, salePrice, saleDate, ticketStatus, contractId, departureDateTime, arrivalDateTime, stationId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, ticket.getId());
            statement.setObject(2, ticket.getTransportType().toString(), java.sql.Types.OTHER);
            statement.setBigDecimal(3, ticket.getPurchasePrice());
            statement.setBigDecimal(4, ticket.getSalePrice());
            statement.setTimestamp(5, ticket.getSaleDate() != null ? Timestamp.valueOf(ticket.getSaleDate()) : null);
            statement.setObject(6, ticket.getTicketStatus().toString(), java.sql.Types.OTHER);
            statement.setObject(7, ticket.getContractId());
            statement.setTimestamp(8, ticket.getDepartureDateTime() != null ? Timestamp.valueOf(ticket.getDepartureDateTime()) : null);
            statement.setTimestamp(9, ticket.getArrivalDateTime() != null ? Timestamp.valueOf(ticket.getArrivalDateTime()) : null);
            statement.setObject(10, ticket.getStation() != null ? ticket.getStation().getId() : null);

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
                LocalDateTime saleDate = resultSet.getObject("saleDate", LocalDateTime.class);
                TicketStatus ticketStatus = TicketStatus.valueOf(resultSet.getString("ticketStatus"));
                UUID contractId = UUID.fromString(resultSet.getString("contractId"));
                LocalDateTime departureDateTime = resultSet.getObject("departureDateTime", LocalDateTime.class);
                LocalDateTime arrivalDateTime = resultSet.getObject("arrivalDateTime", LocalDateTime.class);
                UUID stationId = (UUID) resultSet.getObject("stationId");

                // Display ticket information
                System.out.println("Ticket ID: " + ticketId);
                System.out.println("Transport Type: " + transportType);
                System.out.println("Purchase Price: " + purchasePrice);
                System.out.println("Sale Price: " + salePrice);
                System.out.println("Sale Date: " + (saleDate != null ? saleDate : "N/A"));
                System.out.println("Ticket Status: " + ticketStatus);
                System.out.println("Contract ID: " + contractId);
                System.out.println("Departure DateTime: " + (departureDateTime != null ? departureDateTime : "N/A"));
                System.out.println("Arrival DateTime: " + (arrivalDateTime != null ? arrivalDateTime : "N/A"));
                System.out.println("Station ID: " + (stationId != null ? stationId : "N/A"));
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> findTicketsByStations(List<UUID> stationIds) {
        List<Ticket> tickets = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT t.* FROM Ticket t JOIN Station s ON t.stationId = s.id WHERE s.id IN (");

        for (int i = 0; i < stationIds.size(); i++) {
            queryBuilder.append("?");
            if (i < stationIds.size() - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(")");

        String query = queryBuilder.toString();

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            for (int i = 0; i < stationIds.size(); i++) {
                ps.setObject(i + 1, stationIds.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ticket ticket = new Ticket(
                            (UUID) rs.getObject("id"),
                            TransportType.valueOf(rs.getString("transportType")),
                            rs.getBigDecimal("purchasePrice"),
                            rs.getBigDecimal("salePrice"),
                            rs.getObject("saleDate", LocalDateTime.class),
                            TicketStatus.valueOf(rs.getString("ticketStatus")),
                            (UUID) rs.getObject("contractId"),
                            rs.getObject("departureDateTime", LocalDateTime.class),
                            rs.getObject("arrivalDateTime", LocalDateTime.class),
                            new Station(
                                    (UUID) rs.getObject("stationId"),
                                    rs.getString("stationName"),
                                    rs.getString("stationLocation"),
                                    rs.getDouble("stationDistance")
                            )
                    );
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

}
