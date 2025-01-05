/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_user;

/**
 *
 * @author TenZo
 */
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author TenZo
 */
public class Transactions {
    private final int userId;
    private final String transactionType;
    private final BigDecimal transactionAmount;
    private final Date transactionDate;
    
    public Transactions(int userId, String transactionType, BigDecimal transactionAmount, Date transactionDate ){
        this.userId = userId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate= transactionDate;
    }

    public int getUserId() {
        return userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
    
    
} 
