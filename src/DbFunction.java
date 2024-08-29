import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbFunction {
    public Connection connectToDb(String dbname, String user, String password) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, password);

            if (conn != null) {
                System.out.println("Connection established");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void createTablePartner(Connection conn, String tableName) {
        String query = "CREATE TABLE " + tableName + " (" +
                "id UUID PRIMARY KEY, " +
                "nom_compagnie VARCHAR(255) NOT NULL, " +
                "contact_commercial TEXT, " +
                "type_transport VARCHAR(50), " +
                "zone_geographique VARCHAR(255), " +
                "conditions_speciales TEXT, " +
                "statut_partenaire VARCHAR(50), " +
                "date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        executeUpdate(conn, query);
    }

    public void createTableContract(Connection conn, String tableName) {
        String query = "CREATE TABLE " + tableName + " (" +
                "id UUID PRIMARY KEY, " +
                "date_debut DATE NOT NULL, " +
                "date_fin DATE, " +
                "tarif_special DECIMAL, " +
                "conditions_accord TEXT, " +
                "renouvelable BOOLEAN, " +
                "statut_contrat VARCHAR(50)" +
                "contractiId UUID, " +
                "FOREIGN KEY (partnerId) REFERENCES Partner(id) ON DELETE CASCADE" +

                ")";
        executeUpdate(conn, query);
    }
    public void createTableTicket(Connection conn, String tableName) {
        String query = "CREATE TABLE " + tableName + " (" +
                "id UUID PRIMARY KEY, " +
                "type_transport VARCHAR(50), " +
                "prix_achat DECIMAL, " +
                "prix_vente DECIMAL, " +
                "date_vente TIMESTAMP, " +
                "statut_billet VARCHAR(50)" +
                "contractiId UUID, " +
                "FOREIGN KEY (contractId) REFERENCES Contract(id) ON DELETE SET NULL" +
                ")";
        executeUpdate(conn, query);
    }
    public void createTablePromotionalOffer(Connection conn, String tableName) {
        String query = "CREATE TABLE " + tableName + " (" +
                "id UUID PRIMARY KEY, " +
                "nom_offre VARCHAR(255) NOT NULL, " +
                "description TEXT, " +
                "date_debut DATE, " +
                "date_fin DATE, " +
                "type_reduction VARCHAR(50), " +
                "valeur_reduction DECIMAL, " +
                "conditions TEXT, " +
                "statut_offre VARCHAR(50)" +
                ")";
        executeUpdate(conn, query);
    }

    private void executeUpdate(Connection conn, String query) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
