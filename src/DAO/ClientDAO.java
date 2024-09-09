package DAO;

import Model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO {

    private Connection connection;

    public ClientDAO(Connection connection){
    this.connection=connection;
    }
    public void createClient(Client client){
        String query = "INSERT INTO Client (Email,FirstName,LastName,NumberPhone) VALUES (?,?,?,?)";
    try( PreparedStatement ps =connection.prepareStatement(query)){
        ps.setString(1,client.getEmail());
        ps.setString(2,client.getFirstName());
        ps.setString(3,client.getLastName());
        ps.setString(4,client.getPhoneNumber());
        ps.executeUpdate();

        }catch(Exception e){
       e.printStackTrace();
        }
    }
    public Client getClientByEmail(String email) {
        String query = "SELECT * FROM Client WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getString("Email"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("NumberPhone")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean emailExists(String email) {
        String query = "SELECT 1 FROM Client WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

