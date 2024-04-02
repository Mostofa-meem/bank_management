
package bank_management_system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePin extends JFrame implements ActionListener{
    
    JPasswordField oldfield,newfield;
    JButton confirm,cancle;
    String cardno;
    
    ChangePin(String card){
        cardno=card;
        setLayout(null);
        setVisible(true);
        setSize(650,600);
        setLocation(350,10);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(650, 600, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,650,600);
        add(image);
        
        JLabel deposite= new JLabel("Enter Old Pin");
        deposite.setFont(new Font("Raleway",Font.BOLD,15));
        deposite.setForeground(Color.white);
        deposite.setBounds(145,190,600,30);
        image.add(deposite);
        oldfield=new JPasswordField ();
        oldfield.setBounds(140,220,200,30);
        oldfield.setFont(new Font("Raleway",Font.PLAIN,15));
        image.add(oldfield);
        
        JLabel send= new JLabel("Enter New Pin");
        send.setFont(new Font("Raleway",Font.BOLD,15));
        send.setForeground(Color.white);
        send.setBounds(145,250,600,30);
        image.add(send);
        
        newfield=new JPasswordField();
        newfield.setBounds(140,280,200,30);
        newfield.setFont(new Font("Raleway",Font.PLAIN,15));
        image.add(newfield);
        
        confirm=new JButton("CHANGE");
        confirm.setBounds(240,325,100,30);
        confirm.setBackground(Color.white);
        confirm.setForeground(Color.black);
        confirm.addActionListener(this);
        image.add(confirm);
        
        
        cancle=new JButton("CANCEL");
        cancle.setBounds(140,325,80,30);
        cancle.setBackground(Color.white);
        cancle.setForeground(Color.black);
        cancle.addActionListener(this);
        image.add(cancle);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == cancle)
        {
            setVisible(false);
            new Transaction(cardno).setVisible(true);
        }
        else 
        {
            String oldpin=oldfield.getText();
            String newpin=newfield.getText();
            String query="Select * from signupthree where cardno = '"+cardno+"'and pinno ='"+oldpin+"'";
             Conn c =new Conn();
            try{
                ResultSet rs = c.s.executeQuery(query);
                if(oldpin.equals(newpin)){
                    JOptionPane.showMessageDialog(null, " Old Pin New Pin Same *");
                }
                else if(rs.next()){
                      Conn cc =new Conn();
                       String q="update signupthree set pinno='"+newpin+"' where cardno ='"+cardno+"';";
                        cc.s.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, " Pin Change Successful*");
                     setVisible(false);
                new Transaction(cardno).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, " Wrong Card Number or Pin *");
                     setVisible(false);
                new Transaction(cardno).setVisible(true);
                   
                }
               
            }
            catch (Exception e){
                System.out.println(e);
            }
            
        }
        
    }
    public static void main(String args[]) {
       new ChangePin("");
    }
}
