package main.frames;

import main.Frame;
import main.button.RoundedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.*;

public class SignUpStruct extends Frame implements ActionListener{

    Font mainFont = new Font(null, Font.CENTER_BASELINE, 30);
    Color bloodColor = new Color(240, 132, 134);

    JLabel lbFirstName = new JLabel("First name:");
    JLabel lbLastName = new JLabel("Last name:");
    JLabel lbPhone = new JLabel("Phone:");
    JLabel lbEmail = new JLabel("Email:");
    JLabel lbAddress = new JLabel("Address:");

    JTextField tfFirstName = new JTextField();
    JTextField tfLastName = new JTextField();
    JTextField tfPhone = new JTextField();
    JTextField tfEmail = new JTextField();
    JTextField tfAddress = new JTextField();

    RoundedButton button = new RoundedButton("Sign Up");

    public SignUpStruct(){
        super();
        init();
    }
    public void init(){
        //First Name
        lbFirstName.setBounds(100, 20, 300, 50);
        lbFirstName.setFont(mainFont);
        lbFirstName.setForeground(Color.BLACK);

        tfFirstName.setBounds(400, 20, 600, 50);
        tfFirstName.setFont(mainFont);
        tfFirstName.setForeground(Color.BLACK);

        //Last Name
        lbLastName.setBounds(100, 100, 300, 50);
        lbLastName.setFont(mainFont);
        lbLastName.setForeground(Color.BLACK);

        tfLastName.setBounds(400, 100, 600, 50);
        tfLastName.setFont(mainFont);
        tfLastName.setForeground(Color.BLACK);

        //Phone
        lbPhone.setBounds(100, 200, 300, 50);
        lbPhone.setFont(mainFont);
        lbPhone.setForeground(Color.BLACK);

        tfPhone.setBounds(400, 200, 600, 50);
        tfPhone.setFont(mainFont);
        tfPhone.setForeground(Color.BLACK);

        //Email
        lbEmail.setBounds(100, 300, 300, 50);
        lbEmail.setFont(mainFont);
        lbEmail.setForeground(Color.BLACK);

        tfEmail.setBounds(400, 300, 600, 50);
        tfEmail.setFont(mainFont);
        tfEmail.setForeground(Color.BLACK);

        //Address
        lbAddress.setBounds(100, 400, 300, 50);
        lbAddress.setFont(mainFont);
        lbAddress.setForeground(Color.BLACK);

        tfAddress.setBounds(400, 400, 600, 50);
        tfAddress.setFont(mainFont);
        tfAddress.setForeground(Color.BLACK);

        //Button
        button.setBounds(550,600,250,50);
        button.setFont(mainFont);
        button.setBackground(bloodColor);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.addActionListener(this);

        //Frame Setting
        this.setLayout(null);

        this.add(lbFirstName);
        this.add(lbLastName);
        this.add(lbPhone);
        this.add(lbEmail);
        this.add(lbAddress);

        this.add(tfFirstName);
        this.add(tfLastName);
        this.add(tfPhone);
        this.add(tfEmail);
        this.add(tfAddress);;

        this.add(button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            signUpCompleted(false);
        }
    }

    public void signUpCompleted(boolean isSignUpCompleted){
        if(isSignUpCompleted == true){
            System.out.println("Hello");
        }else{
            JOptionPane.showMessageDialog(null, "You can not sign up", "Warning", JOptionPane.WARNING_MESSAGE);
        }    
    }
}