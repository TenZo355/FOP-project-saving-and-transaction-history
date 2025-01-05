/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger_saving.history;

/**
 *
 * @author TenZo
 */

import data_user.User;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author TenZo
 */
public class PercentageDialog extends JDialog implements ActionListener{
    
    private YesorNoGui yesorNoGui;
    private User user;
    private JButton actionButton;
    private JTextField percentageChoice;
   
            
    
    public PercentageDialog(YesorNoGui yesorNoGui,User user){
         setSize(400,400);
        
        setModal(true);
        
        setLocationRelativeTo(yesorNoGui);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setResizable(false);
        
        setLayout(null);
        
        this.yesorNoGui = yesorNoGui ;
        
        this.user= user;
    }
    
    public void addPercentageToSave(){
        JLabel savingPercenatge = new JLabel("Percentage To Save %");
        savingPercenatge.setBounds(0, 30, super.getWidth(), 40);
        savingPercenatge.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 32));
        savingPercenatge.setHorizontalAlignment(SwingConstants.CENTER  );
        add(savingPercenatge);
        
        percentageChoice = new JTextField();
        percentageChoice.setBounds(15, 190, getWidth()-50, 40);
        percentageChoice.setFont(new Font("MS UI Gothic Bold ",Font.PLAIN,20)); 
        percentageChoice.setHorizontalAlignment(SwingConstants.CENTER  );
        percentageChoice.addActionListener(this);
        add(percentageChoice);
         
        JButton backButton = new JButton("Back");
        backButton.setBounds(15, 500, getWidth() - 50, 50);
        backButton.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 22));
        backButton.addActionListener(this);
        add(backButton);
     } 
    
    public void addActionButton(String actionButtonType){
        actionButton =new JButton(actionButtonType);
        actionButton.setBounds(15, 300, getWidth()-50, 40);
        actionButton.setFont(new Font("Dialog", Font.BOLD, 20));
        actionButton.addActionListener(this);
        add(actionButton);
    }
   
    

    @Override
    public void actionPerformed(ActionEvent e) {
         int percent = Integer.parseInt(percentageChoice.getText());
         try {
          
           
            if (percent < 0 || percent > 100) {
                JOptionPane.showMessageDialog(this, "Please enter a valid percentage (0-100).", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            user.setSavingPercentage(percent);

           BigDecimal debitAmount = getDebitAmountFromDatabase(user.getId());
            BigDecimal savingsAmount = debitAmount.multiply(BigDecimal.valueOf(percent / 100.0));
            user.setSavingBalance(user.getSavingBalance().add(savingsAmount));

           
            updateSavingsBalanceInDatabase(user.getId(), user.getSavingBalance());

            
            JOptionPane.showMessageDialog(this, "Savings percentage saved and balance updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            
            this.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    private BigDecimal getDebitAmountFromDatabase(int userId) {
        
        return BigDecimal.valueOf(1000.00);  
    }

  
    private void updateSavingsBalanceInDatabase(int userId, BigDecimal savingsAmount) {
        
        JOptionPane.showMessageDialog(this,"Updating savings balance for user ID " + userId + " to " + savingsAmount);
    }
     public void processDebit(BigDecimal debitAmount) {
        int percent = Integer.parseInt(percentageChoice.getText());
        user.saveFromDebit(debitAmount, percent);
    }

    public void endOfMonthTransfer() {
        user.transferSavingsToMainBalance();
        System.out.println("Savings have been transferred to your balance.");
    }
}


