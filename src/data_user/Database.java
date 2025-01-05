/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_user;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author TenZo
 */
public class Database {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/main";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASS = "BABY117013_";
    

 public static boolean addTransactionToDatabase(Transactions transactions) {
        try {
            // Establish connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
            
            // SQL query to insert a transaction
            String sql = "INSERT INTO transactions (user_id, transaction_type, transaction_amount, transaction_date) VALUES (?, ?, ?, NOW())";
            PreparedStatement insertTransaction = connection.prepareStatement(sql);
            
            // Set the parameters from the Transactions object
            insertTransaction.setInt(1, transactions.getUserId());
            insertTransaction.setString(2, transactions.getTransactionType());
            insertTransaction.setBigDecimal(3, transactions.getTransactionAmount());
            
            // Execute the query
            insertTransaction.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Method to update the user's current balance in the database
    public static boolean updateCurrentBalance(User user) {
        try {
            // Establish connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
            
            // SQL query to update the current balance of the user
            String sql = "UPDATE users SET current_balance = ? WHERE user_id = ?";
            PreparedStatement updateBalance = connection.prepareStatement(sql);
            
            // Set the parameters
            updateBalance.setBigDecimal(1, user.getSavingBalance()); // Use the new balance
            updateBalance.setInt(2, user.getId()); // The user's unique ID
            
            // Execute the query
            updateBalance.executeUpdate();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to fetch transaction history for a given user
    public static ArrayList<Transactions> getTransactionsHistory(User user) {
        ArrayList<Transactions> transactionHistory = new ArrayList<>();
        try {
            // Establish connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
            
            // SQL query to select all transactions for the user
            String sql = "SELECT * FROM transactions WHERE user_id = ?";
            PreparedStatement selectAllTransaction = connection.prepareStatement(sql);
            selectAllTransaction.setInt(1, user.getId()); // Use the user's unique ID
            
            // Execute the query
            ResultSet resultSet = selectAllTransaction.executeQuery();
            
            // Iterate over the result set and add transactions to the list
            while (resultSet.next()) {
                Transactions transactions = new Transactions(
                    resultSet.getInt("user_id"),
                    resultSet.getString("transaction_type"),
                    resultSet.getBigDecimal("transaction_amount"),
                    resultSet.getDate("transaction_date")
                );
                transactionHistory.add(transactions);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionHistory;
    }
    public int getSavingsPercentage(int userId) {
        String query = "SELECT percentage FROM savings WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("percentage");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Default percentage
    }

    public BigDecimal getTotalDebitAmount(int userId) {
        String query = "SELECT SUM(amount) AS totalDebit FROM transactions WHERE user_id = ? AND transaction_type = 'Debit'";
        BigDecimal totalDebit = BigDecimal.ZERO;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalDebit = rs.getBigDecimal("totalDebit");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalDebit;
    }

    public void updateSavingsBalance(int userId, BigDecimal savingsAmount) {
        String query = "UPDATE savings SET balance = balance + ? WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBigDecimal(1, savingsAmount);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


