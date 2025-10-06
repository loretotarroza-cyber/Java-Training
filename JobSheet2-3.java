import java.sql.*;
import java.time.LocalDateTime;

public class CashIn {
    private static final String URL = "jdbc:mysql://localhost:3306/gcashdb"; // your DB name
    private static final String USER = "root"; // your MySQL username
    private static final String PASSWORD = ""; // your MySQL password

    // Method to cash in
    public void cashIn(int accountId, double amount) {
        Connection conn = null;
        PreparedStatement insertTransaction = null;
        PreparedStatement updateBalance = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false); // start transaction

            // 1. Insert into transaction table
            String insertSQL = "INSERT INTO transaction (amount, name, account_id, date, transferToID, transferFromID) "
                    + "VALUES (?, ?, ?, NOW(), NULL, NULL)";
            insertTransaction = conn.prepareStatement(insertSQL);
            insertTransaction.setDouble(1, amount);
            insertTransaction.setString(2, "Cash-In");
            insertTransaction.setInt(3, accountId);
            insertTransaction.executeUpdate();

            // 2. Update balance table
            String updateSQL = "UPDATE balance SET amount = amount + ? WHERE user_id = ?";
            updateBalance = conn.prepareStatement(updateSQL);
            updateBalance.setDouble(1, amount);
            updateBalance.setInt(2, accountId);
            int rows = updateBalance.executeUpdate();

            if (rows == 0) {
                // If no balance record exists, create one
                String insertBalance = "INSERT INTO balance (amount, user_id) VALUES (?, ?)";
                PreparedStatement createBalance = conn.prepareStatement(insertBalance);
                createBalance.setDouble(1, amount);
                createBalance.setInt(2, accountId);
                createBalance.executeUpdate();
            }

            conn.commit();
            System.out.println("✅ Cash-in successful! Amount: ₱" + amount);

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("❌ Database error: " + e.getMessage());
        } finally {
            try {
                if (insertTransaction != null) insertTransaction.close();
                if (updateBalance != null) updateBalance.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Closing error: " + e.getMessage());
            }
        }
    }

    // Test Method
    public static void main(String[] args) {
        CashIn cashIn = new CashIn();

        // Example tests
        cashIn.cashIn(1, 200);  // first transaction
        cashIn.cashIn(1, 300);  // second transaction
    }
}
