/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger_saving.history;

import data_user.Database;
import data_user.Transactions;
import data_user.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author TenZo
 */
public class TransactionHistoryDialog extends javax.swing.JDialog implements ActionListener{
    private User user;
    private TransactionGui transactionGui;
     private javax.swing.JButton actionButton;
     private javax.swing.JLabel balanceLabel , enterAmountLabel;
     private  ArrayList<Transactions> transactionsHistory;
     private javax.swing.JPanel transactionHisotrypanel;
      private javax.swing.JTextField enterAmountField;
    
    public TransactionHistoryDialog(TransactionGui transactionGui,User user){
        setSize(400,400);
        
        setModal(true);
        
        setLocationRelativeTo(transactionGui);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setResizable(false);
        
        setLayout(null);
        
        this.transactionGui = transactionGui;
        
        this.user= user;
    }
    private void handleTransaction( String transactionType, float amountVal){
        Transactions transactions;
        if(transactionType.equalsIgnoreCase("Debit")){
            user.setSavingBalance(user.getSavingBalance().add(new BigDecimal(amountVal)));
             transactions = new Transactions(user.getId(),transactionType, new BigDecimal(amountVal),null);
        }else{
             user.setSavingBalance(user.getSavingBalance().subtract(new BigDecimal(amountVal)));
        transactions = new Transactions(user.getId(),transactionType, new BigDecimal(-amountVal),null);
        }
    
    
        if(Database.addTransactionToDatabase(transactions) && Database.updateCurrentBalance(user)){
            // show success dialog
            JOptionPane.showMessageDialog(this, transactionType + " Successfully!");

            // reset the fields
//            resetFieldsAndUpdateCurrentBalance();
        }else{
            // show failure dialog
            JOptionPane.showMessageDialog(this, transactionType + " Failed...");
        }

    }
      public void  addCurentBalanceAmount(){
        
     
        
        balanceLabel = new JLabel( "Balance: "+ user.getMainBalance());
        balanceLabel.setBounds(0, 10, getWidth()-20, 20);
        balanceLabel.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 16));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);
        
        enterAmountLabel = new JLabel("Enter Amount");
        enterAmountLabel.setBounds(0, 50, getWidth()-20, 20);
        enterAmountLabel.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 16));
       enterAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountLabel);
        
        enterAmountField = new JTextField();
        enterAmountField.setBounds(15, 80, getWidth()-50, 40);
        enterAmountField.setHorizontalAlignment(SwingConstants.CENTER);
        add(enterAmountField);
        
        
    }

     
       
    
     public void addTransactionHistoryComponents(){
       
       transactionHisotrypanel = new JPanel();
       transactionHisotrypanel.setLayout(new BoxLayout(transactionHisotrypanel,BoxLayout.Y_AXIS ));
       JScrollPane scrollPane = new JScrollPane(transactionHisotrypanel);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       scrollPane.setBounds(0, 20, getWidth()-15, getHeight()-15);
       
       transactionsHistory = Database.getTransactionsHistory(user);
       
       for(int i =0; i < transactionsHistory.size(); i++){
           Transactions transactions = transactionsHistory.get(i);
           
           JLabel transactionHistoryContainer = new JLabel();
           transactionHistoryContainer.setLayout(new BorderLayout());
           
            JLabel transactionTypeLabel = new JLabel(transactions.getTransactionType());
            transactionTypeLabel.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 20));

            // create transaction amount label
            JLabel transactionAmountLabel = new JLabel(String.valueOf(transactions.getTransactionAmount()));
            transactionAmountLabel.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 20));

            // create transaction date label
            JLabel transactionDateLabel = new JLabel(String.valueOf(transactions.getTransactionDate()));
            transactionDateLabel.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 20));
            
            transactionHistoryContainer.add(transactionTypeLabel, BorderLayout.WEST);
            transactionHistoryContainer.add(transactionAmountLabel, BorderLayout.EAST);
            transactionHistoryContainer.add(transactionDateLabel, BorderLayout.SOUTH);
          
            transactionHistoryContainer.setBackground(Color.white);
            transactionHistoryContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            transactionHisotrypanel.add(transactionHistoryContainer);
            
            
        }
       add(scrollPane);
   }
      public void addActionButton(String actionButtonType){
        actionButton =new JButton(actionButtonType);
        actionButton.setBounds(15, 300, getWidth()-50, 40);
        actionButton.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 20));
        actionButton.addActionListener(this);
        add(actionButton);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         String buttonPressed = e.getActionCommand();
    
        float amountVal = Float.parseFloat(enterAmountField.getText());
    
      if ( buttonPressed.equalsIgnoreCase("Debit")){
            
            handleTransaction(buttonPressed, amountVal);
            
        }else{
           
            int result = user.getMainBalance().compareTo(BigDecimal.valueOf(amountVal));
            if(result<0){
                JOptionPane.showMessageDialog(this, "Error : input value is more than balance");
                 return;
            }
        }
      if(buttonPressed.equalsIgnoreCase("Credit")){
                handleTransaction(buttonPressed, amountVal);
               
            
        }      
    }
}
