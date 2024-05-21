import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/a3psc";
    private static final String USER = "root";
    private static final String PASSWORD = "Admin1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                System.out.println("Conexão estabelecida com sucesso!");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}