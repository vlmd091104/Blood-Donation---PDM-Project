package main.frames;

import main.Frame;
import main.button.RoundedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

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

    RoundedButton btSignUp = new RoundedButton("Sign Up");
    RoundedButton btClear = new RoundedButton("Clear");
    RoundedButton btBack = new RoundedButton("Back");
    RoundedButton btUpdate = new RoundedButton("Update");

    boolean Condition;

    public SignUpStruct(){
        super();
        init();
    }
    public void init(){
        //First Name
        lbFirstName.setBounds(100, 50, 300, 50);
        lbFirstName.setFont(mainFont);
        lbFirstName.setForeground(Color.BLACK);

        tfFirstName.setBounds(400, 50, 600, 50);
        tfFirstName.setFont(mainFont);
        tfFirstName.setForeground(Color.BLACK);

        //Last Name
        lbLastName.setBounds(100, 110, 300, 50);
        lbLastName.setFont(mainFont);
        lbLastName.setForeground(Color.BLACK);

        tfLastName.setBounds(400, 110, 600, 50);
        tfLastName.setFont(mainFont);
        tfLastName.setForeground(Color.BLACK);

        //Phone
        lbPhone.setBounds(100, 170, 300, 50);
        lbPhone.setFont(mainFont);
        lbPhone.setForeground(Color.BLACK);

        tfPhone.setBounds(400, 170, 600, 50);
        tfPhone.setFont(mainFont);
        tfPhone.setForeground(Color.BLACK);

        //Email
        lbEmail.setBounds(100, 230, 300, 50);
        lbEmail.setFont(mainFont);
        lbEmail.setForeground(Color.BLACK);

        tfEmail.setBounds(400, 230, 600, 50);
        tfEmail.setFont(mainFont);
        tfEmail.setForeground(Color.BLACK);

        //Address
        lbAddress.setBounds(100, 290, 300, 50);
        lbAddress.setFont(mainFont);
        lbAddress.setForeground(Color.BLACK);

        tfAddress.setBounds(400, 290, 600, 50);
        tfAddress.setFont(mainFont);
        tfAddress.setForeground(Color.BLACK);

        //btSignUp
        btSignUp.setBounds(800,600,200,50);
        btSignUp.setFont(mainFont);
        btSignUp.setBackground(bloodColor);
        btSignUp.setForeground(Color.WHITE);
        btSignUp.setFocusable(false);
        btSignUp.addActionListener(this);

        //btClear
        btClear.setBounds(490,600,200,50);
        btClear.setFont(mainFont);
        btClear.setBackground(bloodColor);
        btClear.setForeground(Color.WHITE);
        btClear.setFocusable(false);
        btClear.addActionListener(this);

        //btBack
        btBack.setBounds(70,600,200,50);
        btBack.setFont(mainFont);
        btBack.setBackground(bloodColor);
        btBack.setForeground(Color.WHITE);
        btBack.setFocusable(false);
        btBack.addActionListener(this);

        //btUpdate using in info frame
        btUpdate.setBounds(800,600,200,50);
        btUpdate.setFont(mainFont);
        btUpdate.setBackground(bloodColor);
        btUpdate.setForeground(Color.WHITE);
        btUpdate.setFocusable(false);
        btUpdate.setVisible(false);
        btUpdate.addActionListener(this);

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
        this.add(tfAddress);

        this.add(btSignUp);
        this.add(btClear);
        this.add(btBack);
        this.add(btUpdate);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //Method for SignUp
    public boolean signUpCompleted(boolean isSignUpCompleted){
        if(isSignUpCompleted == true){
            JOptionPane.showMessageDialog(null, "Sign Up successful", "Message", JOptionPane.DEFAULT_OPTION);
            return true;           
        }else{
            JOptionPane.showMessageDialog(null, "You can not sign up, pls reset the app", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }    
    }

    public boolean confirmToClear(){
        int confirmToClear = JOptionPane.showConfirmDialog(null, "Are you sure to clear all the data", "Confirm ?", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if(confirmToClear == JOptionPane.YES_OPTION){
            return true;
        }else{
            return false;
        }
    }

    public void clearContent(){
        this.tfFirstName.setText("");
        this.tfLastName.setText("");
        this.tfEmail.setText("");
        this.tfPhone.setText("");
        this.tfAddress.setText("");
        this.repaint();
    }
    public boolean isFieldNull(){
        if(tfFirstName.getText().trim().isEmpty() || tfLastName.getText().trim().isEmpty() || tfEmail.getText().trim().isEmpty() || tfAddress.getText().trim().isEmpty() || tfPhone.getText().trim().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    public void processInBackGround(String userType){
        SwingWorker <Void, Void > worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground(){
                btBack.setEnabled(false);
                if(userType.equals("donor")){
                    setVisible(false);

                    DonorHome.getInstance().setVisible(true);
                    DonorHome.restInstance();
                }else if(userType.equals("staff")){
                    setVisible(false);
                    
                    StaffHome.getInstance().setVisible(true);
                    StaffHome.restInstance();
                }
                return null;
            }
            protected void done(){
                btBack.setEnabled(true);
            }
        };
        worker.execute();
    }
    public void setData(String firstName, String lastName, String email, String phone, String address){
        tfFirstName.setText(firstName);
        tfLastName.setText(lastName);
        tfEmail.setText(email);
        tfPhone.setText(phone);
        tfAddress.setText(address);
    }
}