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

    public List<Ticket> getTicketsByStationId(UUID stationId) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT t.id, t.transportType, t.salePrice, " +
                "t.departureDateTime, t.arrivalDateTime, t.contractId, " +
                "s.arrivalLocation, s.departureLocation, s.distance " +
                "FROM tickets t " +
                "JOIN stations s ON t.stationId = s.id " +
                "WHERE s.id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, stationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(UUID.fromString(resultSet.getString("id")));
                    String transportTypeString = resultSet.getString("transportType");
                    TransportType transportType = TransportType.valueOf(transportTypeString);
                    ticket.setTransportType(transportType);
                    ticket.setSalePrice(resultSet.getBigDecimal("salePrice"));
                    ticket.setDepartureDateTime(resultSet.getTimestamp("departureDateTime").toLocalDateTime());
                    ticket.setArrivalDateTime(resultSet.getTimestamp("arrivalDateTime").toLocalDateTime());
                    ticket.setContractId(UUID.fromString(resultSet.getString("contractId")));

                    Station station = new Station();
                    station.setArrivalLocation(resultSet.getString("arrivalLocation"));
                    station.setDepartureLocation(resultSet.getString("departureLocation"));
                    station.setDistance(resultSet.getDouble("distance"));

                    ticket.setStation(station);
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }

}
