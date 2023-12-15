package main.togglePanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import main.button.RoundedButton;
import main.frames.Home;

public class TogglePanel extends JPanel implements ActionListener{

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
        btLogOut.addActionListener(this);

        btInfo.setBounds(50, 125, 100, 50);
        btInfo.setFont(buttonFont);
        btInfo.setBackground(bloodColor);
        btInfo.setForeground(Color.WHITE);
        btInfo.setFocusable(false);
        btInfo.addActionListener(this);
        
        this.setSize(dimension);
        this.setLayout(null);
        this.setBackground(greyColor);
        this.add(btLogOut);
        this.add(btInfo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btLogOut){
            int confirmToLogOut = JOptionPane.showConfirmDialog(null, "Are you sure to clear all the data", "Confirm ?", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(confirmToLogOut == JOptionPane.YES_OPTION){
                confirmToLogOut(true);
            }else{
                confirmToLogOut(false);
            }
        }
    }
    public boolean confirmToLogOut(boolean condition){
        if(condition == true){
            return true;
        }else{
            return false;
        }
    }
}