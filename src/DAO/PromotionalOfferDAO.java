package DAO;

import Model.PromotionalOffer;
import Model.ReductionType;
import Model.OfferStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PromotionalOfferDAO {
    private Connection connection;

    public PromotionalOfferDAO(Connection connection) {
        this.connection = connection;
    }

    public void createPromotionalOffer(PromotionalOffer offer) {
        String query = "INSERT INTO PromotionalOffer (id, offerName, description, startDate, endDate, reductionType, reductionValue, conditions, offerStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, offer.getId());
            pstmt.setString(2, offer.getOfferName());
            pstmt.setString(3, offer.getDescription());
            pstmt.setDate(4, offer.getStartDate() != null ? Date.valueOf(offer.getStartDate()) : null);
            pstmt.setDate(5, offer.getEndDate() != null ? Date.valueOf(offer.getEndDate()) : null);
            pstmt.setString(6, offer.getReductionType().toString());
            pstmt.setBigDecimal(7, offer.getReductionValue());
            pstmt.setString(8, offer.getConditions());
            pstmt.setString(9, offer.getOfferStatus().toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PromotionalOffer getPromotionalOfferById(UUID id) {
        String query = "SELECT * FROM PromotionalOffer WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new PromotionalOffer(
                        (UUID) rs.getObject("id"),
                        rs.getString("offerName"),
                        rs.getString("description"),
                        rs.getDate("startDate") != null ? rs.getDate("startDate").toLocalDate() : null,
                        rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null,
                        ReductionType.valueOf(rs.getString("reductionType")),
                        rs.getBigDecimal("reductionValue"),
                        rs.getString("conditions"),
                        OfferStatus.valueOf(rs.getString("offerStatus"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePromotionalOffer(PromotionalOffer offer) {
        String query = "UPDATE PromotionalOffer SET offerName = ?, description = ?, startDate = ?, endDate = ?, reductionType = ?, reductionValue = ?, conditions = ?, offerStatus = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, offer.getOfferName());
            pstmt.setString(2, offer.getDescription());
            pstmt.setDate(3, offer.getStartDate() != null ? Date.valueOf(offer.getStartDate()) : null);
            pstmt.setDate(4, offer.getEndDate() != null ? Date.valueOf(offer.getEndDate()) : null);
            pstmt.setString(5, offer.getReductionType().toString());
            pstmt.setBigDecimal(6, offer.getReductionValue());
            pstmt.setString(7, offer.getConditions());
            pstmt.setString(8, offer.getOfferStatus().toString());
            pstmt.setObject(9, offer.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePromotionalOffer(UUID id) {
        String query = "DELETE FROM PromotionalOffer WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PromotionalOffer> getAllPromotionalOffers() {
        List<PromotionalOffer> offers = new ArrayList<>();
        String query = "SELECT * FROM PromotionalOffer";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                PromotionalOffer offer = new PromotionalOffer(
                        (UUID) rs.getObject("id"),
                        rs.getString("offerName"),
                        rs.getString("description"),
                        rs.getDate("startDate") != null ? rs.getDate("startDate").toLocalDate() : null,
                        rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null,
                        ReductionType.valueOf(rs.getString("reductionType")),
                        rs.getBigDecimal("reductionValue"),
                        rs.getString("conditions"),
                        OfferStatus.valueOf(rs.getString("offerStatus"))
                );
                offers.add(offer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }
}
