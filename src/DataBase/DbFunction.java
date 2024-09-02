package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbFunction {
    // 1. Créer une instance statique privée
    private static DbFunction instance;

    // 2. Créer une variable pour la connexion
    private Connection conn;

    // 3. Définir un constructeur privé
    private DbFunction() {}

    // 4. Fournir une méthode publique statique pour obtenir l'instance unique
    public static DbFunction getInstance() {
        if (instance == null) {
            synchronized (DbFunction.class) { // Synchronisation pour le multi-threading
                if (instance == null) {
                    instance = new DbFunction();
                }
            }
        }
        return instance;
    }

    // 5. Méthode pour établir la connexion à la base de données
    public Connection connectToDb(String dbname, String user, String password) {
        if (conn == null) {  // Si la connexion n'est pas encore établie
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
        }
        return conn;
    }

    // Méthode pour fermer la connexion
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null; // Réinitialiser la connexion à null après fermeture
                System.out.println("Connection closed.");
            } catch (Exception e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
}


