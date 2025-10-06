import java.sql.*;

public class CashTransfer {
    private static final String URL = "jdbc:mysql://localhost:3306/gcashdb"; // your DB name
    private static final String USER = "root"; // your MySQL username
    private static final String PASSWORD = ""; // your MySQL password

    public void cashTransfer(int fromUserId, int toUserId, double amount) {
        Connection conn = null;
        PreparedStatement checkBalanceStmt = null;
        PreparedStatement updateSenderStmt = null;
        PreparedStatement updateReceiverStmt = null;
        PreparedStatement insertTransactionStmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false); // Begin transaction

            // 1️⃣ Check if both accounts exist
            String checkUserSQL = "SELECT COUNT(*) FROM balance WHERE user_id = ?";
            PreparedStatement checkUserStmt = conn.prepareStatement(checkUserSQL);
            checkUserStmt.setInt(1, fromUserId);
            ResultSet rs1 = checkUserStmt.executeQuery();
            rs1.next();
            if (rs1.getInt(1) == 0) {
                System.out.println("❌ Sender account not found.");
                return;
            }
            checkUserStmt.setInt(1, toUserId);
            ResultSet rs2 = checkUserStmt.executeQuery();
            rs2.next();
            if (rs2.getInt(1) == 0) {
                System.out.println("❌ Receiver account not found.");
                return;
            }

            // 2️⃣ Check sender balance
            String checkBalanceSQL = "SELECT amount FROM balance WHERE user_id = ?";
            checkBalanceStmt = conn.prepareStatement(checkBalanceSQL);
            checkBalanceStmt.setInt(1, fromUserId);
            ResultSet rs = checkBalanceStmt.executeQuery();
            double senderBalance = 0;
            if (rs.next()) {
                senderBalance = rs.getDouble("amount");
            }

            if (senderBalance < amount) {
                System.out.println("❌ Insufficient funds. Transfer cancelled.");
                return;
            }

            // 3️⃣ Deduct from sender
            String deductSQL = "UPDATE balance SET amount = amount - ? WHERE user_id = ?";
            updateSenderStmt = conn.prepareStatement(deductSQL);
            updateSenderStmt.setDouble(1, amount);
            updateSenderStmt.setInt(2, fromUserId);
            updateSenderStmt.executeUpdate();

            // 4️⃣ Add to receiver
            String addSQL = "UPDATE balance SET amount = amount + ? WHERE user_id = ?";
            updateReceiverStmt = conn.prepareStatement(addSQL);
            updateReceiverStmt.setDouble(1, amount);
            updateReceiverStmt.setInt(2, toUserId);
            updateReceiverStmt.executeUpdate();

            // 5️⃣ Record transaction
            String insertSQL = "INSERT INTO transaction (amount, name, account_id, transferToID, transferFromID) "
                    + "VALUES (?, ?, ?, ?, ?)";
            insertTransactionStmt = conn.prepareStatement(insertSQL);
            insertTransactionStmt.setDouble(1, amount);
            insertTransactionStmt.setString(2, "Cash Transfer");
            insertTransactionStmt.setInt(3, fromUserId);
            insertTransactionStmt.setInt(4, toUserId);
            insertTransactionStmt.setInt(5, fromUserId);
            insertTransactionStmt.executeUpdate();

            conn.commit();
            System.out.println("✅ Transfer successful! ₱" + amount + " sent from User " + fromUserId + " to User " + toUserId);

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
                System.out.println("⚠️ Transaction rolled back due to an error.");
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("❌ Database error: " + e.getMessage());
        } finally {
            try {
                if (checkBalanceStmt != null) checkBalanceStmt.close();
                if (updateSenderStmt != null) updateSenderStmt.close();
                if (updateReceiverStmt != null) updateReceiverStmt.close();
                if (insertTransactionStmt != null) insertTransactionStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("❌ Closing error: " + e.getMessage());
            }
        }
    }

    // Test the cash transfer function
    public static void main(String[] args) {
        CashTransfer transfer = new CashTransfer();

        // Example: Transfer ₱150 from User 1 to User 2
        transfer.cashTransfer(1, 2, 150);
    }
}
