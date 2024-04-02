
package bank_management_system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignupThree extends JFrame implements ActionListener{
    JCheckBox c1;
    JButton finish,cancle;
    String qone,qtwo;
    String cardnum,pinnum,from_no;
    
    SignupThree(String frm,String q1, String q2){
    from_no=frm;
    qone=q1;
    qtwo=q2;
    setLayout(null);
    JLabel text= new JLabel("Page 3:  Account Info");
        text.setFont(new Font("Raleway",Font.BOLD,20));
        text.setBounds(140,50,600,40);
        add(text);
      
    Random ran=new Random();
    cardnum=""+Math.abs(Math.abs(ran.nextLong()%1000000000000000L)+1000000000000000L);
    ran=new Random();
    pinnum=""+Math.abs(Math.abs(ran.nextInt()%1000)+1000);
    
    JLabel cardno= new JLabel("Card No : "+cardnum);
        cardno.setFont(new Font("Raleway",Font.BOLD,18));
        cardno.setBounds(100,100,600,30);
        add(cardno);
        
    JLabel pin= new JLabel("PIN  No : "+pinnum);
        pin.setFont(new Font("Raleway",Font.BOLD,18));
        pin.setBounds(100,140,600,30);
        add(pin);
    
   
    
    c1=new JCheckBox("I Accepted all the ...........");
    c1.setBackground(Color.white);
    c1.setFont(new Font("Raleway",Font.ROMAN_BASELINE,20));
    c1.setBounds(100,220,300,30);
    add(c1);
    
    finish= new JButton("Finish");
    finish.setBackground(Color.black);
    finish.setBounds(200,270,100,30);
    finish.setFont(new Font("Raleway",Font.BOLD,18));
    finish.setForeground(Color.white);
    add(finish);
    finish.addActionListener(this);
           
    cancle= new JButton("Cancle");
    cancle.setBackground(Color.black);
    cancle.setBounds(350,270,100,30);
    cancle.setFont(new Font("Raleway",Font.BOLD,18));
    cancle.setForeground(Color.white);
    add(cancle);
    cancle.addActionListener(this);
    
     setSize(660,600);
        setVisible(true);
        setLocation(350,10);
        
     
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==finish){
          
            if(c1.isSelected()){
                
                try{
                Conn c =new Conn();
                c.s.executeUpdate(qone);
                c.s.executeUpdate(qtwo);
                
                
    
                String query="insert into signupthree values ('"+from_no+"','"+cardnum+"','"+pinnum+"')";
                
                
                
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, " Card Num : "+cardnum+"\n Pin :"+pinnum+"\n");//validation
                
                new Deposit(cardnum).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(SignupThree.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             else{
                   JOptionPane.showMessageDialog(null, " CheckBox Requried *");//validation 
                }          
        }
        setVisible(false);
    }

}
