package main.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.button.RoundedButton;
import main.encrypt.Encrypt;
import main.sql.ConnectSQL;

public class showPassword extends HomeStruct{

    RoundedButton btReset = new RoundedButton("Reset");

    private static showPassword instance;

    public showPassword(String Who){
        super();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btReset.setBounds(315,150, 150, 50);
        btReset.setFont(mainFont.deriveFont(30f));
        btReset.setBackground(bloodColor);
        btReset.setForeground(Color.WHITE);
        btReset.setEnabled(true);
        btReset.setFocusable(false);
        btReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(pfPassword.getPassword());
                if(password.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Password can not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    ConnectSQL.resetPassword(Who, HomeStruct.getID(), Encrypt.encrypt(password));
                    dispose();
                }         
            }
        });

        this.setSize(780, 250);
        this.add(btReset);
    }
    public static showPassword getInstance(String userType) {
        if (instance == null) {
            instance = new showPassword(userType);
        }
        return instance;
    }
}
