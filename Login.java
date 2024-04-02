/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package bank_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
    JButton login,clear,signup;
    JTextField cardTextField;
    JPasswordField pinTextField;
    Login(){
        setTitle("AMT");
        setLayout(null);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/logo.jpg"));
        Image i2=i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel label=new JLabel(i3);
        label.setBounds(70,10,100,100);
        add(label);
        
        JLabel text=new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward",Font.BOLD,38));
        text.setBounds(200,40,400,40);
        add(text);
        
      
        //Card No
        JLabel cardno=new JLabel("Card No: ");
        cardno.setFont(new Font("Raleway",Font.BOLD,28));
        cardno.setBounds(120,150,150,30);
        add(cardno);
        
        cardTextField=new JTextField();
        cardTextField.setBounds(250,150,250,30);
        cardTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextField);
        
        //Pin
        JLabel pin=new JLabel("PIN: ");
        pin.setFont(new Font("Raleway",Font.BOLD,28));
        pin.setBounds(182,200,400,40);
        add(pin);
        
        pinTextField=new JPasswordField();
        pinTextField.setBounds(250,205,250,30);
        pinTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);
        
        
        
        login=new JButton("Sign IN");
        login.setBounds(250,280,100,30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);
        
        
        clear=new JButton("Clear");
        clear.setBounds(400,280,100,30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);
        
        
        signup=new JButton("Sign UP");
        signup.setBounds(250,330,250,30);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);
        
        
        getContentPane().setBackground(Color.white);
        
        setSize(800,480);
        setVisible(true);
        setLocation(350,200);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == clear)
        {
            cardTextField.setText("");
            pinTextField.setText("");
        }
        else if(ae.getSource()== login)
        {
            String cardno=cardTextField.getText();
            String pinno=pinTextField.getText();
            String query="Select * from signupthree where cardno = '"+cardno+"'and pinno ='"+pinno+"'";
             Conn c =new Conn();
            try{
                ResultSet rs = c.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Transaction(cardno).setVisible(true);  
                }
                else{
                    JOptionPane.showMessageDialog(null, " Wrong Card Number or Pin *");
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        else{
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    
    public static void main(String args[]) {
        new Login();
        
    }
}
