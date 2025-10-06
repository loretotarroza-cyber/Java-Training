import java.sql.*;

public class CheckBalance {
    // JDBC connection details
    private static final String URL = "jdbc:mysql://localhost:3306/gcashdb";  // Replace with your DB name
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = ""; // Replace with your MySQL password

    public double getBalance(int userId) {
        double balance = -1;

        String query = "SELECT amount FROM balance WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("amount");
            } else {
                System.out.println("No balance record found for user ID: " + userId);
            }

        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

        return balance;
    }

    public static void main(String[] args) {
        CheckBalance checker = new CheckBalance();
        int userId = 1;  // Example: checking user with ID 1
        double balance = checker.getBalance(userId);

        if (balance >= 0) {
            System.out.println("User ID " + userId + " balance: ₱" + balance);
        }
    }
}
