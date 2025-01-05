/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ledger_saving.history;

import data_user.User;
import java.math.BigDecimal;
import javax.swing.SwingUtilities;

/**
 *
 * @author TenZo
 */
public class Ledger_SavingHistory {
     private static User user;

    
     public static void main(String[] args) {
   
        user = new User(1, new BigDecimal("200.00"));
        

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                
                new TransactionGui(user).setVisible(true);
                 // Create user with example balances
//        User user = new User(1, new BigDecimal("500.00"));
//        user.setSavingBalance(new BigDecimal("200.00")); // Example savings balance

                
            }
        });
    }
}
    
    

