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
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author TenZo
 */
public abstract class BaseFrame extends javax.swing.JFrame{
    protected User user;
 
    public BaseFrame(String title){
        initialize(title);
    }
    public  BaseFrame(String title, User user){
        this.user= user;
        initialize(title);
    }
    

    private void initialize(String title){
        setTitle(title);
        
        setSize(650, 650);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(null);
        
        setResizable(false);
        
        setLocationRelativeTo(null);
        
        addGuiComponents();
}
    protected abstract void addGuiComponents();
}
 
