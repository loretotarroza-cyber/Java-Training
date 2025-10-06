package gcashapp;

import java.util.*;
import java.sql.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final String URL = "jdbc:mysql://localhost:3306/gcashdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection conn;
    private static int loggedInUserId = -1;
    private static String loggedInUserName = "";

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to GCashApp Database");
            loginMenu();
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Error: " + e.getMessage());
        }
    }

    // 🔹 Login System
    private static void loginMenu() {
        System.out.print("\nEnter Account ID to Login: ");
        loggedInUserId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter your name: ");
        loggedInUserName = sc.nextLine();

        System.out.println("\n👋 Welcome, " + loggedInUserName + "!");
        mainMenu();
    }

    // 🔹 Main Menu
    private static void mainMenu() {
        CashIn cashIn = new CashIn();
        CashTransfer cashTransfer = new CashTransfer();
        Transactions transactions = new Transactions();

        while (true) {
            System.out.println("\n=== GCash Main Menu ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Cash-In");
            System.out.println("3. Cash-Transfer");
            System.out.println("4. View My Transactions");
            System.out.println("5. Logout");
            System.out.print("Select option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to Cash-In: ");
                    double cashInAmt = sc.nextDouble();
                    cashIn.cashIn(loggedInUserId, cashInAmt);
                    break;
                case 3:
                    System.out.print("Enter receiver ID: ");
                    int receiver = sc.nextInt();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmt = sc.nextDouble();
                    cashTransfer.cashTransfer(loggedInUserId, receiver, transferAmt);
                    break;
                case 4:
                    transactions.viewUserAll(loggedInUserId);
                    break;
                case 5:
                    System.out.println("\n👋 Logging out...");
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }

            System.out.print("\nDo you want another transaction? (y/n): ");
            sc.nextLine(); // consume newline
            String again = sc.nextLine();
            if (again.equalsIgnoreCase("n")) {
                System.out.println("👋 Thank you for using GCashApp!");
                break;
            }
        }
    }

    // 🔹 Check Balance
    private static void checkBalance() {
        String sql = "SELECT balance FROM account WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, loggedInUserId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    System.out.println("\n💰 Current Balance: ₱" + balance);
                } else {
                    System.out.println("❌ Account not found!");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error checking balance: " + e.getMessage());
        }
    }
}
