package DAO;

import Model.Partner;
import Model.PartnerStatus;
import Model.TransportType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PartnerDAO {
    private Connection connection;

    public PartnerDAO(Connection connection) {
        this.connection = connection;
    }

    public void createPartner(Partner partner) {
        String query = "INSERT INTO Partner (id, nom_compagnie, contact_commercial, type_transport, zone_geographique, conditions_speciales, statut_partenaire, date_creation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, partner.getId());
            pstmt.setString(2, partner.getNomCompagnie());
            pstmt.setString(3, partner.getContactCommercial());
            pstmt.setString(4, partner.getTypeTransport().toString());
            pstmt.setString(5, partner.getZoneGeographique());
            pstmt.setString(6, partner.getConditionsSpeciales());
            pstmt.setString(7, partner.getStatutPartenaire().toString());
            pstmt.setTimestamp(8, new Timestamp(partner.getDateCreation().getTime()));

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
                        rs.getString("nom_compagnie"),
                        rs.getString("contact_commercial"),
                        TransportType.valueOf(rs.getString("type_transport")),
                        rs.getString("zone_geographique"),
                        rs.getString("conditions_speciales"),
                        PartnerStatus.valueOf(rs.getString("statut_partenaire")),
                        rs.getTimestamp("date_creation")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePartner(Partner partner) {
        String query = "UPDATE Partner SET nom_compagnie = ?, contact_commercial = ?, type_transport = ?, zone_geographique = ?, conditions_speciales = ?, statut_partenaire = ?, date_creation = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, partner.getNomCompagnie());
            pstmt.setString(2, partner.getContactCommercial());
            pstmt.setString(3, partner.getTypeTransport().toString());
            pstmt.setString(4, partner.getZoneGeographique());
            pstmt.setString(5, partner.getConditionsSpeciales());
            pstmt.setString(6, partner.getStatutPartenaire().toString());
            pstmt.setTimestamp(7, new Timestamp(partner.getDateCreation().getTime()));
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
                String nomCompagnie = resultSet.getString("nom_compagnie");
                String contactCommercial = resultSet.getString("contact_commercial");
                TransportType typeTransport = TransportType.valueOf(resultSet.getString("type_transport"));
                String zoneGeographique = resultSet.getString("zone_geographique");
                String conditionsSpeciales = resultSet.getString("conditions_speciales");
                PartnerStatus statutPartenaire = PartnerStatus.valueOf(resultSet.getString("statut_partenaire"));
                Timestamp timestamp = resultSet.getTimestamp("date_creation");
                Date dateCreation = new Date(timestamp.getTime());
                Partner partner = new Partner(id, nomCompagnie, contactCommercial, typeTransport, zoneGeographique, conditionsSpeciales, statutPartenaire, dateCreation);
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

