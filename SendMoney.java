
package bank_management_system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMoney extends JFrame implements ActionListener{
    
    JTextField card_send,send_amount;
    JButton confirm,cancle;
    String cardno;
    
    SendMoney(String card){
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
        
        JLabel deposite= new JLabel("Recipient Card Number");
        deposite.setFont(new Font("Raleway",Font.BOLD,15));
        deposite.setForeground(Color.white);
        deposite.setBounds(145,190,600,30);
        image.add(deposite);
        card_send=new JTextField();
        card_send.setBounds(140,220,200,30);
        card_send.setFont(new Font("Raleway",Font.PLAIN,15));
        image.add(card_send);
        
        JLabel send= new JLabel("Enter Amount");
        send.setFont(new Font("Raleway",Font.BOLD,15));
        send.setForeground(Color.white);
        send.setBounds(145,250,600,30);
        image.add(send);
        
        send_amount=new JTextField();
        send_amount.setBounds(140,280,200,30);
        send_amount.setFont(new Font("Raleway",Font.PLAIN,15));
        image.add(send_amount);
        
        confirm=new JButton("SEND");
        confirm.setBounds(260,325,80,30);
        confirm.setBackground(Color.white);
        confirm.setForeground(Color.black);
        confirm.addActionListener(this);
        image.add(confirm);
        
        
        cancle=new JButton("Cancle");
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
            String cardsend=card_send.getText();
            String query="Select * from signupthree where cardno = '"+cardsend+"';";
             Conn c =new Conn();
            try{
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next())
                {
                    Conn cs=new Conn(); 
                    String am=send_amount.getText(); 
                    try{
                         String q1="select * from transaction where cardno = '"+cardno+"'";
                 ResultSet res=cs.s.executeQuery(q1);
                 // System.out.println(amount);
                 int balance=0;
                 while(res.next())
                 {
                     if(res.getString("type").equals("Deposit")){
                         
                         balance+=Integer.parseInt(res.getString("amount"));
                     }
                     else{
                         balance-=Integer.parseInt(res.getString("amount"));
                     }
                    
                 }
                        
                        if(balance<Integer.parseInt(am))
                        {
                            JOptionPane.showMessageDialog(null, " Insaficient Balance");//validation
                            setVisible(false);
                            new Transaction(cardno);
                        }
                        else{
                            
                                    
                            String type="Withdraw";
                             java.util.Date date=new java.util.Date();
                             String query_trans="insert into transaction values('"+cardno+"','"+date+"','"+type+"','"+am+"');";
                             Conn cc=new Conn(); 
                            try{
                                  cc.s.executeUpdate(query_trans);
                            }catch (SQLException ex){                         
                             System.out.println(ex);
                           }
                            
                            type="Deposit";
                            String query_send="insert into transaction values('"+cardsend+"','"+date+"','"+type+"','"+am+"');";
                             Conn ccc=new Conn(); 
                            try{
                                  ccc.s.executeUpdate(query_send);
                            }catch (SQLException ex){                         
                             System.out.println(ex);
                           }
                            
                        }
                        JOptionPane.showMessageDialog(null, " Send Successful !!!");
                        setVisible(false);
                            new Transaction(cardno);
                     }
                     catch(SQLException ee){
                       
                      System.out.println(ee);
                     }  
                    
                }
                 else{
                    JOptionPane.showMessageDialog(null, " Wrong Card Number !!!");
                }
            }
            catch (HeadlessException | SQLException e){
                System.out.println(e);
            }           
        }
        
    }
    public static void main(String args[]) {
       new SendMoney("");
    }
}
