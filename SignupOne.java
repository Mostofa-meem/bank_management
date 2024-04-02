package bank_management_system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupOne extends JFrame implements ActionListener {

    JTextField nameTextField,fnameTextField,emailTextField,addressTextField,pinTextField;
    JButton nxt;
    int random;
    JRadioButton male,female,married,single;
    JDateChooser dateChooser;
    
    SignupOne(){
        setTitle("APPLICATION FORM");
        setLayout(null);
        
        
        
        Random ran=new Random();
        random=Math.abs(ran.nextInt()%1000+10000);
        
        JLabel formno =new JLabel("APPLICATION FORM NO "+random);
        formno.setFont(new Font("Raleway",Font.BOLD,28));
        formno.setBounds(140,10,600,40);
        add(formno);
        
        JLabel personal =new JLabel("Page 1 : Personal Details");
        personal.setFont(new Font("Raleway",Font.BOLD,18));
        personal.setBounds(240,60,600,30);
        add(personal);
        
        JLabel name =new JLabel("Name:");
        name.setFont(new Font("Raleway",Font.BOLD,18));
        name.setBounds(140,100,140,30);
        add(name);
        
        nameTextField=new JTextField();
        nameTextField.setFont(new Font("Raleway",Font.BOLD,14));
        nameTextField.setBounds(300,100,250,30);
        add(nameTextField);
        
        
        //
        JLabel fname =new JLabel("Fathers Name:");
        fname.setFont(new Font("Raleway",Font.BOLD,18));
        fname.setBounds(140,140,140,30);
        add(fname);
        fnameTextField=new JTextField();
        fnameTextField.setFont(new Font("Raleway",Font.BOLD,14));
        fnameTextField.setBounds(300,140,250,30);
        add(fnameTextField);
        
        //
        JLabel dob =new JLabel("Date of Birth :");
        dob.setFont(new Font("Raleway",Font.BOLD,18));
        dob.setBounds(140,180,140,30);
        add(dob);
        dateChooser=new JDateChooser();
        dateChooser.setBounds(300,180,250,30);
        add(dateChooser);
        
        
        JLabel gender =new JLabel("Gender :");
        gender.setFont(new Font("Raleway",Font.BOLD,18));
        gender.setBounds(140,220,140,30);
        add(gender);
        male=new JRadioButton("Male");
        female=new JRadioButton("Female");
        add(male);
        add(female);
        
        male.setBounds(300,220,100,30);
        male.setBackground(Color.white);
        female.setBounds(450,220,100,30);
        female.setBackground(Color.white);
        ButtonGroup gendergroup=new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);
                
        //
        JLabel email =new JLabel("Email Address :");
        email.setFont(new Font("Raleway",Font.BOLD,18));
        email.setBounds(140,260,140,30);
        add(email);
        emailTextField=new JTextField();
        emailTextField.setFont(new Font("Raleway",Font.BOLD,14));
        emailTextField.setBounds(300,260,250,30);
        add(emailTextField);
        
        //
        JLabel marital =new JLabel("Marital Status :");
        marital.setFont(new Font("Raleway",Font.BOLD,18));
        marital.setBounds(140,300,140,30);
        add(marital);
         married=new JRadioButton("Married");
         single =new JRadioButton("Single");
        add(married);
        add(single);
        married.setBounds(300,300,100,30);
        married.setBackground(Color.white);
        single.setBounds(450,300,100,30);
        single.setBackground(Color.white);
        ButtonGroup marrital=new ButtonGroup();
        marrital.add(married);
        marrital.add(single);
        
        JLabel address =new JLabel("Address :");
        address.setFont(new Font("Raleway",Font.BOLD,18));
        address.setBounds(140,340,140,30);
        add(address);
        addressTextField=new JTextField();
        addressTextField.setFont(new Font("Raleway",Font.BOLD,14));
        addressTextField.setBounds(300,340,250,30);
        add(addressTextField);
        
        //
        JLabel pincode =new JLabel("Pin Code :");
        pincode.setFont(new Font("Raleway",Font.BOLD,18));
        pincode.setBounds(140,380,140,30);
        add(pincode);
        pinTextField=new JTextField();
        pinTextField.setFont(new Font("Raleway",Font.BOLD,14));
        pinTextField.setBounds(300,380,250,30);
        add(pinTextField);
        
        nxt=new JButton("NEXT ");
        nxt.setBounds(450,450,100,30);
        nxt.setBackground(Color.black);
        nxt.setForeground(Color.white);
        nxt.addActionListener(this);
        add(nxt);
        
        setSize(650,600);
        setLocation(350,10);
        setVisible(true);
        getContentPane().setBackground(Color.white);
    }
    public void actionPerformed(ActionEvent ae)
    {
        String fromnum= ""+ random;
        String name=nameTextField.getText();
        String fname=fnameTextField.getText();
        String dob=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
        String gender;
        if(male.isSelected())
            gender="Male";
        else 
            gender="Female";
        String email=emailTextField.getText();
        String marital;
        if(married.isSelected())
            marital="Married";
        else marital="Single";
        String address=addressTextField.getText();
        String pin=pinTextField.getText();
        
        try{
            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "Name is Requried *");//validation 
            }else{
              //  Conn c =new Conn();
               String query="insert into signup values ('"+fromnum+"','"+name+"','"+fname+"','"+dob+"','"+gender+"','"+email+"','"+marital+"','"+address+"','"+pin+"')";
              //  c.s.executeUpdate(query);
                
                setVisible(false);
                new SignupTwo(fromnum,query).setVisible(true);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String args[]) {
        new SignupOne();
    }
}
