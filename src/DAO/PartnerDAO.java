package DAO;

import Model.Partner;
import Model.PartnerStatus;
import Model.TransportType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PartnerDAO {
    private Connection connection;

    public PartnerDAO(Connection connection) {
        this.connection = connection;
    }

    public void createPartner(Partner partner) {
        String query = "INSERT INTO Partner (id, companyName, commercialContact, transportType, geographicArea, specialConditions, partnerStatus, creationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, partner.getId());
            pstmt.setString(2, partner.getCompanyName());
            pstmt.setString(3, partner.getCommercialContact());
            pstmt.setString(4, partner.getTransportType().toString());
            pstmt.setString(5, partner.getGeographicArea());
            pstmt.setString(6, partner.getSpecialConditions());
            pstmt.setString(7, partner.getPartnerStatus().toString());
            pstmt.setTimestamp(8, new Timestamp(partner.getCreationDate().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Partner getPartnerById(UUID id) {
        String query = "SELECT * FROM Partner WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Partner(
                        (UUID) rs.getObject("id"),
                        rs.getString("companyName"),
                        rs.getString("commercialContact"),
                        TransportType.valueOf(rs.getString("transportType")),
                        rs.getString("geographicArea"),
                        rs.getString("specialConditions"),
                        PartnerStatus.valueOf(rs.getString("partnerStatus")),
                        rs.getTimestamp("creationDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePartner(Partner partner) {
        String query = "UPDATE Partner SET companyName = ?, commercialContact = ?, transportType = ?, geographicArea = ?, specialConditions = ?, partnerStatus = ?, creationDate = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, partner.getCompanyName());
            pstmt.setString(2, partner.getCommercialContact());
            pstmt.setString(3, partner.getTransportType().toString());
            pstmt.setString(4, partner.getGeographicArea());
            pstmt.setString(5, partner.getSpecialConditions());
            pstmt.setString(6, partner.getPartnerStatus().toString());
            pstmt.setTimestamp(7, new Timestamp(partner.getCreationDate().getTime()));
            pstmt.setObject(8, partner.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Partner> getAllPartners() {
        List<Partner> partners = new ArrayList<>();
        String query = "SELECT * FROM Partner";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String companyName = resultSet.getString("companyName");
                String commercialContact = resultSet.getString("commercialContact");
                TransportType transportType = TransportType.valueOf(resultSet.getString("transportType"));
                String geographicArea = resultSet.getString("geographicArea");
                String specialConditions = resultSet.getString("specialConditions");
                PartnerStatus partnerStatus = PartnerStatus.valueOf(resultSet.getString("partnerStatus"));
                Timestamp timestamp = resultSet.getTimestamp("creationDate");
                Date creationDate = new Date(timestamp.getTime());
                Partner partner = new Partner(id, companyName, commercialContact, transportType, geographicArea, specialConditions, partnerStatus, creationDate);
                partners.add(partner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return partners;
    }

    public void deletePartner(UUID id) {
        String query = "DELETE FROM Partner WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
