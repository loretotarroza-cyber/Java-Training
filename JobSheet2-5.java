import java.sql.*;

public class Transactions {
    private static final String URL = "jdbc:mysql://localhost:3306/gcashdb"; // Update if needed
    private static final String USER = "root"; // MySQL username
    private static final String PASSWORD = ""; // MySQL password

    // 🔹 Method 1: View all transactions
    public void viewAll() {
        String sql = "SELECT * FROM transaction ORDER BY date DESC";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== ALL TRANSACTIONS ===");
            while (rs.next()) {
                System.out.println("Transaction ID: " + rs.getInt("id"));
                System.out.println("Type: " + rs.getString("name"));
                System.out.println("Amount: ₱" + rs.getDouble("amount"));
                System.out.println("Account ID: " + rs.getInt("account_id"));
                System.out.println("Transfer To ID: " + rs.getInt("transferToID"));
                System.out.println("Transfer From ID: " + rs.getInt("transferFromID"));
                System.out.println("Date: " + rs.getTimestamp("date"));
                System.out.println("-------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error viewing all transactions: " + e.getMessage());
        }
    }

    // 🔹 Method 2: View all transactions by user ID
    public void viewUserAll(int userId) {
        String sql = "SELECT * FROM transaction WHERE account_id = ? OR transferToID = ? OR transferFromID = ? ORDER BY date DESC";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("\n=== TRANSACTIONS FOR USER ID: " + userId + " ===");
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("Transaction ID: " + rs.getInt("id"));
                    System.out.println("Type: " + rs.getString("name"));
                    System.out.println("Amount: ₱" + rs.getDouble("amount"));
                    System.out.println("Transfer To ID: " + rs.getInt("transferToID"));
                    System.out.println("Transfer From ID: " + rs.getInt("transferFromID"));
                    System.out.println("Date: " + rs.getTimestamp("date"));
                    System.out.println("-------------------------------");
                }
                if (!found) {
                    System.out.println("No transactions found for this user.");
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error viewing user transactions: " + e.getMessage());
        }
    }

    // 🔹 Method 3: View a single transaction by transaction ID
    public void viewTransaction(int transactionId) {
        String sql = "SELECT * FROM transaction WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n=== TRANSACTION DETAILS ===");
                    System.out.println("Transaction ID: " + rs.getInt("id"));
                    System.out.println("Type: " + rs.getString("name"));
                    System.out.println("Amount: ₱" + rs.getDouble("amount"));
                    System.out.println("Account ID: " + rs.getInt("account_id"));
                    System.out.println("Transfer To ID: " + rs.getInt("transferToID"));
                    System.out.println("Transfer From ID: " + rs.getInt("transferFromID"));
                    System.out.println("Date: " + rs.getTimestamp("date"));
                } else {
                    System.out.println("❌ No transaction found with ID " + transactionId);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error viewing transaction: " + e.getMessage());
        }
    }

    // 🔹 Test main
    public static void main(String[] args) {
        Transactions tx = new Transactions();

        // View all transactions
        tx.viewAll();

        // View transactions by a specific user
        tx.viewUserAll(1);

        // View specific transaction by ID
        tx.viewTransaction(3);
    }
}
