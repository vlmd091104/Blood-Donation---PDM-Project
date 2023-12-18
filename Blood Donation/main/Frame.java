package main;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{
    final private int screenWidth = 1280;
    final private int screenHeight = 720;
    Dimension dimension = new Dimension(screenWidth, screenHeight);

    public Frame(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(dimension);
        this.setResizable(false);
        this.setTitle("Blood Donation");
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        ImageIcon Logo = new ImageIcon(getClass().getResource("/res/BloodDonation_Logo.png"));
        this.setIconImage(Logo.getImage());
    }
}