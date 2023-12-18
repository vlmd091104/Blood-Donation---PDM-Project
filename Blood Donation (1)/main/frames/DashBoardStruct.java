package main.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.Frame;
import main.togglePanel.TogglePanel;

public class DashBoardStruct extends Frame implements ActionListener{

    Font buttonFont = new Font(null, Font.CENTER_BASELINE, 20);
    Color greyColor = new Color(217, 217, 217);
    Color bloodColor = new Color(240, 132, 134);

    JButton btMultiFunction = new JButton("<html><div style='text-align:center;'>&#9776;</div></html>");

    TogglePanel togglePanel = new TogglePanel();

    boolean isMultiFunctionClicked = false;
    public static boolean confirmToLogOut;

    public DashBoardStruct(){
        super();

        btMultiFunction.setBounds(1200, 20, 54, 30);
        btMultiFunction.setFont(buttonFont);
        btMultiFunction.setBackground(bloodColor);
        btMultiFunction.setForeground(greyColor);
        btMultiFunction.setFocusable(false);
        btMultiFunction.addActionListener(this);

        togglePanel.setLocation(1100, 100);
        togglePanel.setVisible(false);

        togglePanel.btLogOut.addActionListener(this);
        togglePanel.btInfo.addActionListener(this);
        
        this.setLayout(null);
        this.add(btMultiFunction);
        this.add(togglePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btMultiFunction){
            if(isMultiFunctionClicked == false){
                this.togglePanel.setVisible(true);
                isMultiFunctionClicked = true;
            }else{
                this.togglePanel.setVisible(false);
                isMultiFunctionClicked = false;
            }
        }if(e.getSource() == togglePanel.btLogOut){
            if(TogglePanel.confirmToLogOut()){
                Home home = new Home();
                this.dispose();
                home.setVisible(true);
            }
        }
    }
}
