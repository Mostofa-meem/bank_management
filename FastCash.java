
package bank_management_system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class FastCash extends JFrame implements ActionListener{
JButton fiveh,one,two,five,seven,ten,back;
String cardno;   
FastCash(String card)
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
        
        JLabel trans=new JLabel("Select Withdrawl Amount");
        trans.setBounds(200,260,300,30);
        trans.setForeground(Color.white);
        trans.setFont(new Font("system",Font.BOLD,16));
        image.add(trans);        
                
         fiveh=new JButton("TK 500");
        fiveh.setBounds(160,370,110,25); 
        fiveh.addActionListener(this);
        image.add(fiveh);
        
         one=new JButton("TK 1000");
        one.setBounds(310,370,140,25);   
        one.addActionListener(this);
        image.add(one);
       
         two=new JButton("TK 2000");
        two.setBounds(160,400,110,25); 
        two.addActionListener(this);
        image.add(two);
        
         five=new JButton("TK 5000");
        five.setBounds(310,400,140,25);  
        five.addActionListener(this);
        image.add(five);
        
         seven=new JButton("TK 7500");
        seven.setBounds(160,430,110,25); 
        seven.addActionListener(this);
        image.add(seven);
        
         ten=new JButton("TK 10000");
        ten.setBounds(310,430,140,25);  
        ten.addActionListener(this);
        image.add(ten);
        
         
        
         back=new JButton("BACK");
        back.setBounds(310,460,140,25);
        back.addActionListener(this);
        image.add(back);
        
        
       setVisible(true);
   }
    public void actionPerformed(ActionEvent e){
         if(e.getSource()==back){
             setVisible(false);
             new Transaction(cardno).setVisible(true);
         }
         else{
             String amount = ((JButton)e.getSource()).getText().substring(3);
            
             Conn c=new Conn(); 
             try{
                 String q1="select * from transaction where cardno = '"+cardno+"'";
                 ResultSet rs=c.s.executeQuery(q1);
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
                 
                 if(balance<Integer.parseInt(amount))
                 {
                     JOptionPane.showMessageDialog(null, " Insaficient Balance");//validation
                 }
                 else{
                     String type="Withdraw";
                     java.util.Date date=new java.util.Date();
                     String query_trans="insert into transaction values('"+cardno+"','"+date+"','"+type+"','"+amount+"');";
                     Conn cc=new Conn(); 
                    System.out.println(query_trans);
                    try{
                         cc.s.executeUpdate(query_trans);
                         JOptionPane.showMessageDialog(null, " Withdrawl succesful Amount : "+amount);//validation
                         setVisible(false); 
                         new Transaction(cardno).setVisible(true);
                     }catch (SQLException ex){
                         
                             System.out.println(ex);
                      }
                 }
             }
             catch(SQLException ee){
                 System.out.println(ee);
             }
             
         }
         
         
     }
    public static void main(String args[]) {
       new FastCash("");
    }
    
}
