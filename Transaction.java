
package bank_management_system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Transaction extends JFrame implements ActionListener{
JButton deposit,balance,widthdraw,send,ministatement,fastcash,changepin,exit;
String cardno;   
Transaction(String card)
   {
       cardno=card;
       setLayout(null);
       setSize(700,700);      
       setLocation(350,10);
       
       
       ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,800,800);
        add(image);
        
        JLabel trans=new JLabel("Please Select Your Transaction");
        trans.setBounds(175,260,300,30);
        trans.setForeground(Color.white);
        trans.setFont(new Font("system",Font.BOLD,16));
        image.add(trans);        
                
         deposit=new JButton("Deposit");
        deposit.setBounds(160,370,110,25); 
        deposit.addActionListener(this);
        image.add(deposit);
        
         balance=new JButton("Balance Enquiry");
        balance.setBounds(310,370,140,25);   
        balance.addActionListener(this);
        image.add(balance);
       
         widthdraw=new JButton("Withdraw");
        widthdraw.setBounds(160,400,110,25); 
        widthdraw.addActionListener(this);
        image.add(widthdraw);
        
         send=new JButton("Send Money");
        send.setBounds(310,400,140,25);  
        send.addActionListener(this);
        image.add(send);
        
         ministatement=new JButton("Mini Statement");
        ministatement.setBounds(160,430,110,25); 
        ministatement.addActionListener(this);
        image.add(ministatement);
        
         fastcash=new JButton("Fast Cash");
        fastcash.setBounds(310,430,140,25);  
        fastcash.addActionListener(this);
        image.add(fastcash);
        
         changepin=new JButton("Change PIN");
        changepin.setBounds(160,460,110,25);   
        changepin.addActionListener(this);
        image.add(changepin);
        
         exit=new JButton("Exit");
        exit.setBounds(310,460,140,25);
        exit.addActionListener(this);
        image.add(exit);
        
        
       setVisible(true);
   }
    public void actionPerformed(ActionEvent e){
         if(e.getSource()==deposit){
             setVisible(false);
             new Deposit(cardno);
         }
         else if(e.getSource()==balance){
             try{
             Conn cc=new Conn(); 
             String q1="select * from transaction where cardno = '"+cardno+"'";
                 ResultSet rs=cc.s.executeQuery(q1);
                 // System.out.println(amount);
                 int balance=0;
                 while(rs.next())
                 {
                     if(rs.getString("type").equals("Deposit")){
                         
                         balance+=Integer.parseInt(rs.getString("amount"));
                     }
                     else{
                         balance-=Integer.parseInt(rs.getString("amount"));
                     }
                    
                 }
                 JOptionPane.showMessageDialog(null, " Available Balance : "+balance);//validation
             }
             catch(SQLException ee){
                 System.out.println(ee);
             }
         }
         else if(e.getSource()==widthdraw){
            setVisible(false);
             new Withdraw(cardno);
         }
         else if(e.getSource()==send){
             setVisible(false);
             new SendMoney(cardno).setVisible(true);
         }
         else if(e.getSource()==ministatement){
              new MiniStatement(cardno).setVisible(true);
         }
         else if(e.getSource()==fastcash){
             setVisible(false);
             new FastCash(cardno).setVisible(true);
         }
         else if(e.getSource()==changepin){
             setVisible(false);
             new ChangePin(cardno).setVisible(true);
         }
         else if(e.getSource()==exit){
             setVisible(false);
         }
         
         
     }
    public static void main(String args[]) {
       new Transaction("");
    }
    
}
