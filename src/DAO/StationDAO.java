package DAO;

import Model.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StationDAO {
    private final Connection connection;

    public StationDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Station station) throws SQLException {
        String query = "INSERT INTO Station (id, departureLocation, arrivalLocation, distance) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, station.getId());
            stmt.setString(2, station.getDepartureLocation());
            stmt.setString(3, station.getArrivalLocation());
            stmt.setDouble(4, station.getDistance());
            stmt.executeUpdate();
        }
    }
}
