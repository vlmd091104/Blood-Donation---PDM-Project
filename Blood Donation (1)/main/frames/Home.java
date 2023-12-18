package main.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import main.Frame;
import main.button.RoundedButton;

public class Home extends Frame implements KeyListener{

    Font buttonFont = new Font(null, Font.CENTER_BASELINE, 50);
    Color greyColor = new Color(217, 217, 217);
    Color bloodColor = new Color(240, 132, 134);

    JPanel pnBackGround = new JPanel();

    JLabel lbTitle = new JLabel();
    JLabel lbLogo = new JLabel();

    RoundedButton btStaff = new RoundedButton("Staff");
    RoundedButton btDonor = new RoundedButton("Donor");

    public Home(){
        super();

        /*Stuffs set */
        //Title
        lbTitle.setBounds(600, 100, 600, 100);
        lbTitle.setFont(new Font(null, Font.CENTER_BASELINE, 80));
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setText("Blood Donation");

        //Image
        ImageIcon image = new ImageIcon(getClass().getResource("/res/BloodBag.png"));
        lbLogo.setBounds(100, 210, 200, 300);
        lbLogo.setIcon(image);

        //Button
        btStaff.setBounds(600, 360, 200, 100);
        btStaff.setFont(buttonFont);
        btStaff.setBackground(greyColor);
        btStaff.setForeground(Color.BLACK);
        btStaff.setFocusable(false);
        btStaff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                staffButton();
            }
        });

        btDonor.setBounds(1000, 360, 200, 100);
        btDonor.setFont(buttonFont);
        btDonor.setBackground(greyColor);
        btDonor.setForeground(Color.BLACK);
        btDonor.setFocusable(false);
        btDonor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                donorButton();
            }
            
        });

        //BackGround
        pnBackGround.setBounds(0, 0, 1280, 720);
        pnBackGround.setBackground(bloodColor);
        pnBackGround.setLayout(null);
        
        pnBackGround.add(lbTitle);
        pnBackGround.add(lbLogo);
        pnBackGround.add(btStaff);
        pnBackGround.add(btDonor);

        /*Frame set */
        this.add(pnBackGround);
        this.addKeyListener(this);
    }

    public void staffButton(){
        StaffHome staffHome = new StaffHome();
        this.dispose();
        staffHome.setVisible(true);
    }
    public void donorButton(){
        DonorHome donorHome = new DonorHome();
        this.dispose();
        donorHome.setVisible(true);
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_A){
            AdminHome.getInstance().setVisible(true);
            this.dispose();
        }
    }
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}