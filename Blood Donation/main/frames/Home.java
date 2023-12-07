package main.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import main.Frame;
import main.button.RoundedButton;

public class Home extends Frame{

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
        this.setVisible(true);
    }
    public void staffButton(){
        System.out.println("Staff");
                        
        StaffHome staffHome = new StaffHome();
        this.setVisible(false);
        staffHome.setVisible(true);
        
        System.out.println("Staff Home");
    }
    public void donorButton(){
        System.out.println("Donor");

        DonorHome donorHome = new DonorHome();
        
        this.setVisible(false);
        donorHome.setVisible(true);

        System.out.println("Donor Home");
    }
}