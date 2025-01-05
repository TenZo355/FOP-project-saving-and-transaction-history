/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger_saving.history;

import data_user.User;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author TenZo
 */
public class TransactionGui extends BaseFrame implements ActionListener{
      private javax.swing.JTextField savingBalanceField;
    public javax.swing.JTextField saavingBalanceField(){
        return savingBalanceField;}
    
   public TransactionGui(User user){
         super("Banking app", user);
   }
     
    protected void addGuiComponents(){
        
        JLabel  savingBalanceLabel = new JLabel ("Saving Balance");
        savingBalanceLabel.setBounds(0, 80, getWidth()-10, 30);
         savingBalanceLabel.setFont(new Font("MS UI Gothic Bold ", Font.PLAIN, 16));
       savingBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
       add( savingBalanceLabel);
       
       JTextField  savingBalanceField = new JTextField("RM"+ user.getSavingBalance());
        savingBalanceField.setBounds(15, 120, getWidth()-50, 40);
        savingBalanceField.setHorizontalAlignment(SwingConstants.RIGHT);
        savingBalanceField.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 28));
        savingBalanceField.setEditable(false);
       add( savingBalanceField);
       
       
        JButton depositButton = new JButton("Debit");
        depositButton.setBounds(15, 180, getWidth() - 50, 50);
        depositButton.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 22));
        depositButton.addActionListener(this);
        add(depositButton);

        // withdraw button
        JButton withdrawButton = new JButton("Credit");
        withdrawButton.setBounds(15, 250, getWidth() - 50, 50);
        withdrawButton.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 22));
        withdrawButton.addActionListener(this);
        add(withdrawButton);
        
    JButton pastTransactionButton = new JButton("Transaction history");
        pastTransactionButton.setBounds(15, 320, getWidth() - 50, 50);
        pastTransactionButton.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 22));
        pastTransactionButton.addActionListener(this);
        add(pastTransactionButton);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(15, 500, getWidth() - 50, 50);
        backButton.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 22));
        backButton.addActionListener(this);
        add(backButton);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        
         if ( buttonPressed.equalsIgnoreCase("Back")){
         
           this.dispose();
           new YesorNoGui(user).setVisible(true);
           return;
      }
        TransactionHistoryDialog transactionHistoryDialog = new TransactionHistoryDialog(this, user);
        
       transactionHistoryDialog.setTitle(buttonPressed);
       
        if(buttonPressed.equalsIgnoreCase("Debit")|| buttonPressed.equalsIgnoreCase("credit")
              ){
            
            transactionHistoryDialog.addCurentBalanceAmount();

            transactionHistoryDialog.addActionButton(buttonPressed);
        }transactionHistoryDialog.setVisible(true);
    }
}

