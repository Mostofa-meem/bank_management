
package bank_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {
    JTextField Nidnum;
    JComboBox religionbox,typebox,incombox,edubox;
    JButton next;
    String from,query_one;
    SignupTwo(String fromnum,String q1){
        query_one=q1;
        setLayout(null);
        from=fromnum;
        JLabel text= new JLabel("Page 2: Personal Information");
        text.setFont(new Font("Raleway",Font.BOLD,20));
        text.setBounds(140,50,600,40);
        add(text);
                
        JLabel religion=new JLabel("Religion :");
        religion.setFont(new Font("Raleway",Font.BOLD,18));
        religion.setBounds(100,100,140,30);
        add(religion);
        
        String religionarr[]={"Islam","Hindu","Others"}; 
        religionbox=new JComboBox(religionarr);
        religionbox.setFont(new Font("Raleway",Font.PLAIN,18));
        religionbox.setBounds(250,105,300,25);
        religionbox.setBackground(Color.white);
        add(religionbox);
                
        JLabel type=new JLabel("Account Type:");
        type.setFont(new Font("Raleway",Font.BOLD,18));
        type.setBounds(100,140,140,30);
        add(type);
        
        String typearr[]={"Silver","Gold","Platiniam","Diamond"}; 
        typebox=new JComboBox(typearr);
        typebox.setFont(new Font("Raleway",Font.PLAIN,18));
        typebox.setBounds(250,145,300,25);
        typebox.setBackground(Color.white);
        add(typebox);
        
        JLabel income=new JLabel("Income :");
        income.setFont(new Font("Raleway",Font.BOLD,18));
        income.setBounds(100,180,140,30);
        add(income);
        
        String incomearr[]={"<1,50,000","<2,50,000","<5,00,000","<15,00,000"};
        incombox=new JComboBox(incomearr);
        incombox.setFont(new Font("Raleway",Font.PLAIN,18));
        incombox.setBounds(250,185,300,25);
        incombox.setBackground(Color.white);
        add(incombox);
        
        
        JLabel Education=new JLabel("Education :");
        Education.setFont(new Font("Ralway",Font.BOLD,18));
        Education.setBounds(100,220,140,30);
        add(Education);
        
        String eduarr[]={"SSC","HSC","B.Sc","PHD"};
        edubox=new JComboBox(eduarr);
        edubox.setFont(new Font("Raleway",Font.PLAIN,18));
        edubox.setBounds(250,225,300,25);
        edubox.setBackground(Color.white);
        add(edubox);
        
        JLabel nid=new JLabel("NID :");
        nid.setFont(new Font("Ralway",Font.BOLD,18));
        nid.setBounds(100,260,140,30);
        add(nid);
        
        Nidnum=new JTextField();
        Nidnum.setFont(new Font("Raleway",Font.PLAIN,18));
        Nidnum.setBounds(250,265,300,25);
        Nidnum.setBackground(Color.white);
        add(Nidnum);
        
        next=new JButton("NEXT");
        next.setBounds(450,300,100,30);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);
        
        setSize(660,600);
        setVisible(true);
        setLocation(350,10);
    }
    
    public void actionPerformed(ActionEvent e){
        String religion=(String)religionbox.getSelectedItem();
        String typee=(String)typebox.getSelectedItem();
        String incom=(String)incombox.getSelectedItem();
        String Edu=(String)edubox.getSelectedItem();
        String nid=Nidnum.getText();
        
        try {
        //Conn c =new Conn();
        String query="insert into signuptwo values ('"+from+"','"+religion+"','"+typee+"','"+incom+"','"+Edu+"','"+nid+"')";
       // c.s.executeUpdate(query);
       setVisible(false);
       new SignupThree(from,query_one,query);
        //String 
        }catch(Exception ae){
            System.out.println(ae);
        }  
    }
}
