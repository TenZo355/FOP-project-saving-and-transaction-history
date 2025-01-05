/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger_saving.history;




import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import data_user.User;





public class YesorNoGui extends BaseFrame implements ActionListener{
    
    private User user;
    public YesorNoGui(User user){
        super("Ledger System Saving");
        this.user = user;
        
    }
           
    
    protected void addGuiComponents(){
        
        JLabel savingDebitLabel = new JLabel("Saving from debit?");
        savingDebitLabel.setBounds(0, 20, super.getWidth(), 40);
        savingDebitLabel.setFont(new Font("MS UI Gothic Bold ", Font.BOLD, 32));
        savingDebitLabel.setHorizontalAlignment(SwingConstants.CENTER  );
        add(savingDebitLabel);
        


        JButton yes = new JButton("Yes");
        yes.setBounds(5, 200, (getWidth()-30)/2, 40);
        yes.setFont(new Font("MS UI Gothic Bold ",Font.PLAIN,28));
        yes.addActionListener(this);
        add(yes);
        
        JButton no = new JButton("No");
        no.setBounds((getWidth()-30)/2+20, 200, (getWidth()-30)/2, 40);
        no.setFont(new Font("MS UI Gothic Bold ",Font.PLAIN,28));
        no.addActionListener(this);
        add(no);
        
        
    }
       
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
      
//      if ( buttonPressed.equalsIgnoreCase("Back")){
//          new YesorNoGui().setVisible(true);
//           this.dispose();
//           return;
//      }
        PercentageDialog percentageDialog = new PercentageDialog(this, user);
        
        percentageDialog.setTitle("Percentage to save");
     
        
        if(buttonPressed.equalsIgnoreCase("yes")){
            percentageDialog.addPercentageToSave();
            percentageDialog.addActionButton("Confirm");
            percentageDialog.setVisible(true);
        }
         if (buttonPressed.equalsIgnoreCase("no")) {
        
    }
        
    }
   
}