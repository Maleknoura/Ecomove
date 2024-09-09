package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbFunction {
    private static DbFunction instance;

    private Connection conn;

    private DbFunction() {}

    public static DbFunction getInstance() {
        if (instance == null) {
            synchronized (DbFunction.class) {
                if (instance == null) {
                    instance = new DbFunction();
                }
            }
        }
        return instance;
    }


    public Connection connectToDb(String dbname, String user, String password) {
        if (conn == null) {
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


    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Connection closed.");
            } catch (Exception e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

}


