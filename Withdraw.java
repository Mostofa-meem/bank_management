
package bank_management_system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Withdraw extends JFrame implements ActionListener{
    
    JTextField deposit;
    JButton confirm,cancle;
    String cardno;
    
    Withdraw(String card){
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
        
        JLabel deposite= new JLabel("Enter Amount :");
        deposite.setFont(new Font("Raleway",Font.BOLD,18));
        deposite.setForeground(Color.white);
        deposite.setBounds(180,240,600,30);
        image.add(deposite);
        deposit=new JTextField();
        deposit.setBounds(140,280,200,30);
        deposit.setFont(new Font("Raleway",Font.PLAIN,15));
        image.add(deposit);
        
        confirm=new JButton("Withdraw");
        confirm.setBounds(240,320,100,30);
        confirm.setBackground(Color.white);
        confirm.setForeground(Color.black);
        confirm.addActionListener(this);
        image.add(confirm);
        
        
        cancle=new JButton("Cancle");
        cancle.setBounds(140,320,80,30);
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
            String add=deposit.getText();
            Date date=new Date();
            String type="Withdraw";
            if(add.equals(""))
            {
                JOptionPane.showMessageDialog(null, " Amount Requried *");//validation
            }
            else{
                String query_trans="insert into transaction values('"+cardno+"','"+date+"','"+type+"','"+add+"');";
                Conn c=new Conn(); 
                try{
                    c.s.executeUpdate(query_trans);
                    JOptionPane.showMessageDialog(null, " Withdrawl succesful Amount : "+add);//validation
                    setVisible(false);
                    new Transaction(cardno).setVisible(true);
                }catch (SQLException e){
                 System.out.println(e);
                }

            }
            
        }
        
    }
    public static void main(String args[]) {
        new Withdraw("");
    }
}

