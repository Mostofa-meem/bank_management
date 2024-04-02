
package bank_management_system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement  extends JFrame implements ActionListener{

    JButton b1;
    String cardno;
    MiniStatement(String card){
        cardno=card;
        
        JLabel mini=new JLabel();
        mini.setBounds(20, 140, 400, 200);
        add(mini);
        
        JLabel l4 = new JLabel();
        l4.setBounds(20, 400, 300, 20);
        add(l4);
        
        setLayout(null);
        setVisible(true);
        setSize(400,600);
        setLocation(350,10);
       
        JLabel text= new JLabel("Name Nai Bank");
        text.setFont(new Font("Raleway",Font.BOLD,15));
        text.setForeground(Color.black);
        text.setBounds(140,40,600,30);
        add(text);
        
        Conn c =new Conn();
        String name="";
        String nameq="select name from signup s join  signupthree t where s.fromno=t.fromno and t.cardno='"+cardno+"';";
        try{
                ResultSet rs = c.s.executeQuery(nameq);
                if(rs.next()){
                name=rs.getString("name");
                }
        }
        catch(Exception e){
            System.out.println(e);
        }
        JLabel cd= new JLabel("Card : "+cardno.substring(0,4)+"-XXXX-XXXX-"+cardno.substring(12));
        cd.setFont(new Font("Raleway",Font.BOLD,15));
        cd.setForeground(Color.black);
        cd.setBounds(50,80,600,30);
        add(cd);
        
        JLabel nm= new JLabel("Name : "+name);
        nm.setFont(new Font("Raleway",Font.BOLD,15));
        nm.setForeground(Color.black);
        nm.setBounds(50,110,600,30);
        add(nm);
        String stq="select * from transaction where cardno='"+cardno+"';";
        
        try{
                int balance=0;
                ResultSet res = c.s.executeQuery(stq);
                while(res.next())
                {  
                    mini.setText(mini.getText() + "<html>"+res.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + res.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + res.getString("amount") + "<br><br><html>");
                    if(res.getString("type").equals("Deposit")){
                         
                         balance+=Integer.parseInt(res.getString("amount"));
                     }
                     else{
                         balance-=Integer.parseInt(res.getString("amount"));
                     }
                }
             l4.setText("Your total Balance is TK "+balance);
             b1 = new JButton("Exit");     
             add(b1);        
             b1.addActionListener(this);
             b1.setBounds(20, 500, 100, 25);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
       
    }
     public void actionPerformed(ActionEvent a){
        this.setVisible(false);
    }
    public static void main(String args[]) {
       new MiniStatement("");
    }
}
