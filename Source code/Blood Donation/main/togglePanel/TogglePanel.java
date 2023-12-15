package main.togglePanel;

import javax.swing.*;

import java.awt.*;

import main.button.RoundedButton;

public class TogglePanel extends JPanel{

    Dimension dimension = new Dimension(200, 300);
    
    Font buttonFont = new Font(null, Font.CENTER_BASELINE, 12);
    Color greyColor = new Color(217, 217, 217);
    Color bloodColor = new Color(240, 132, 134);

    public RoundedButton btLogOut = new RoundedButton("Log out");
    public RoundedButton btInfo = new RoundedButton("Info");

    public TogglePanel(){

        btLogOut.setBounds(50, 25, 100, 50);
        btLogOut.setFont(buttonFont);
        btLogOut.setBackground(bloodColor);
        btLogOut.setForeground(Color.WHITE);
        btLogOut.setFocusable(false);

        btInfo.setBounds(50, 125, 100, 50);
        btInfo.setFont(buttonFont);
        btInfo.setBackground(bloodColor);
        btInfo.setForeground(Color.WHITE);
        btInfo.setFocusable(false);
        
        this.setSize(dimension);
        this.setLayout(null);
        this.setBackground(greyColor);
        this.add(btLogOut);
        this.add(btInfo);
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