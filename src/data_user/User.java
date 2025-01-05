/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_user;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author TenZo
 */
public class User {
   
//    private final int percentage;
    
    private BigDecimal mainBalance,savingBalance;
    private int savingPercentage, id;
    private Date lastTransferDate;
    
    public User(int id, BigDecimal mainBalance){


        this.id = id;
        this.mainBalance = mainBalance;
        this.savingBalance = BigDecimal.ZERO;
        this.savingPercentage = 0;
         this.lastTransferDate = new Date();
        
    }
     public int getId() {
        return id;
     }
    public BigDecimal getMainBalance() {
        return mainBalance;
    }
    public void setMainBalance(BigDecimal mainBalance) {
        this.mainBalance = mainBalance;
    }
    public int getSavingPercentage() {
        return savingPercentage;
    }
    public void setSavingPercentage(int savingPercentage) {
        this.savingPercentage = savingPercentage;
    } 
     public BigDecimal getSavingBalance() {
        return savingBalance;
    }
    public void setSavingBalance(BigDecimal savingBalance) {
        this.savingBalance = savingBalance;
    }
       

    
 public BigDecimal saveFromDebit(BigDecimal debitAmount, int savingPercentage) {
        BigDecimal savingAmount = debitAmount.multiply(BigDecimal.valueOf(savingPercentage / 100.0));
        this.savingBalance = this.savingBalance.add(savingAmount);
        return savingBalance;
        
    }

    public void transferSavingsToMainBalance() {
        // Check if it's the start of a new month
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);

        Calendar lastTransferCalendar = Calendar.getInstance();
        lastTransferCalendar.setTime(this.lastTransferDate);
        int lastTransferMonth = lastTransferCalendar.get(Calendar.MONTH);
        int lastTransferYear = lastTransferCalendar.get(Calendar.YEAR);

       
        if (currentMonth != lastTransferMonth || currentYear != lastTransferYear) {
            this.mainBalance = this.mainBalance.add(this.savingBalance); // Transfer savings
            this.savingBalance = BigDecimal.ZERO; // Reset savings balance
            this.lastTransferDate = new Date(); // Update last transfer date
        }
    }
}