package DAO;

import Model.Contract;
import Model.ContractStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractDAO {
    private Connection connection;

    public ContractDAO(Connection connection) {
        this.connection = connection;
    }

    public void createContract(Contract contract) {
        String query = "INSERT INTO Contract (id, startDate, endDate, specialRate, agreementConditions, renewable, contractStatus, partnerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, contract.getId());
            pstmt.setDate(2, Date.valueOf(contract.getStartDate()));
            pstmt.setDate(3, contract.getEndDate() != null ? Date.valueOf(contract.getEndDate()) : null);
            pstmt.setBigDecimal(4, contract.getSpecialRate());
            pstmt.setString(5, contract.getAgreementConditions());
            pstmt.setBoolean(6, contract.isRenewable());
            pstmt.setString(7, contract.getContractStatus().toString());
            pstmt.setObject(8, contract.getPartnerId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contract getContractById(UUID id) {
        String query = "SELECT * FROM Contract WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Contract(
                        (UUID) rs.getObject("id"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null,
                        rs.getBigDecimal("specialRate"),
                        rs.getString("agreementConditions"),
                        rs.getBoolean("renewable"),
                        ContractStatus.valueOf(rs.getString("contractStatus")),
                        (UUID) rs.getObject("partnerId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateContract(Contract contract) {
        String query = "UPDATE Contract SET startDate = ?, endDate = ?, specialRate = ?, agreementConditions = ?, renewable = ?, contractStatus = ?, partnerId = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDate(1, Date.valueOf(contract.getStartDate()));
            pstmt.setDate(2, contract.getEndDate() != null ? Date.valueOf(contract.getEndDate()) : null);
            pstmt.setBigDecimal(3, contract.getSpecialRate());
            pstmt.setString(4, contract.getAgreementConditions());
            pstmt.setBoolean(5, contract.isRenewable());
            pstmt.setString(6, contract.getContractStatus().toString());
            pstmt.setObject(7, contract.getPartnerId());
            pstmt.setObject(8, contract.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        String query = "SELECT * FROM Contract";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Contract contract = new Contract(
                        (UUID) rs.getObject("id"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate") != null ? rs.getDate("endDate").toLocalDate() : null,
                        rs.getBigDecimal("specialRate"),
                        rs.getString("agreementConditions"),
                        rs.getBoolean("renewable"),
                        ContractStatus.valueOf(rs.getString("contractStatus")),
                        (UUID) rs.getObject("partnerId")
                );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    public void deleteContract(UUID id) {
        String query = "DELETE FROM Contract WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
