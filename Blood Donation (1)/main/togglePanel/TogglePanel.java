package main.togglePanel;

import javax.swing.*;

import java.awt.*;

import main.button.RoundedButton;

public class TogglePanel extends JPanel{

    Dimension dimension = new Dimension(150, 400);
    
    Font buttonFont = new Font(null, Font.CENTER_BASELINE, 10);
    Color greyColor = new Color(217, 217, 217);
    Color bloodColor = new Color(240, 132, 134);

    public RoundedButton btLogOut = new RoundedButton("Log out");
    public RoundedButton btInfo = new RoundedButton("Info");
    public RoundedButton btResetPassword = new RoundedButton("Reset Password");
    public RoundedButton btForm = new RoundedButton("Form");

    public TogglePanel(){

        btLogOut.setBounds(25, 25, 100, 50);
        btLogOut.setFont(buttonFont);
        btLogOut.setBackground(bloodColor);
        btLogOut.setForeground(Color.WHITE);
        btLogOut.setFocusable(false);

        btInfo.setBounds(25, 100, 100, 50);
        btInfo.setFont(buttonFont);
        btInfo.setBackground(bloodColor);
        btInfo.setForeground(Color.WHITE);
        btInfo.setFocusable(false);

        btResetPassword.setBounds(25, 175, 100, 50);
        btResetPassword.setFont(buttonFont);
        btResetPassword.setBackground(bloodColor);
        btResetPassword.setForeground(Color.WHITE);
        btResetPassword.setFocusable(false);

        btForm.setBounds(25, 250, 100, 50);
        btForm.setFont(buttonFont);
        btForm.setBackground(bloodColor);
        btForm.setForeground(Color.WHITE);
        btForm.setFocusable(false);
        
        this.setSize(dimension);
        this.setLayout(null);
        this.setBackground(greyColor);
        this.add(btLogOut);
        this.add(btInfo);
        this.add(btResetPassword);
        this.add(btForm);
    }

    public static boolean confirmToLogOut(){
        int toLogOut = JOptionPane.showConfirmDialog(null, "Are you sure to log out", "Confirm ?", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if(toLogOut == JOptionPane.YES_OPTION){
            return true;
        }else{
            return false;
        }
    }
}