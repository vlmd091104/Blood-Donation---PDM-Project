package main.frames;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.sql.ConnectSQL;

import java.awt.event.ActionEvent;


public class AdminDashBoard extends DashBoardStruct{

    JLabel queryLabel = new JLabel("Query:");
    JLabel resultLabel = new JLabel("Result:");
    JButton runButton = new JButton("Run");
    JButton clearButton = new JButton("Clear");
    JTextField queryField = new JTextField();
    JScrollPane resultPanel = new JScrollPane();

    public AdminDashBoard(){
        super();

        this.togglePanel.btInfo.setVisible(false);
        this.togglePanel.btResetPassword.setVisible(false);
        this.togglePanel.btForm.setVisible(false);
        this.togglePanel.setSize(200, 100);

        queryLabel.setBounds(100, 100, 150, 70);
        queryLabel.setFont(new Font(null, Font.ROMAN_BASELINE, 30));
        resultLabel.setBounds(100, 200, 200, 70);
        resultLabel.setFont(new Font(null, Font.ROMAN_BASELINE, 30));

        queryField.setBounds(270, 100, 500, 70);
        queryField.setFont(new Font(null, Font.ROMAN_BASELINE, 20));

        /*Buttons Configuration */
        runButton.setBounds(800, 100, 100, 75);
        runButton.setFont(new Font(null, Font.ROMAN_BASELINE, 25));
        runButton.setFocusable(false);
        runButton.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(ActionEvent e){
                if(queryField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please input query string!", "Message", JOptionPane.WARNING_MESSAGE);
                }else{
                    ConnectSQL.adminQuery(queryField.getText(), resultPanel);
                    resultPanel.revalidate();
                    resultPanel.repaint();
                }
            }
        });

        clearButton.setBounds(950, 100, 100, 75);
        clearButton.setFont(new Font(null, Font.ROMAN_BASELINE, 25));
        clearButton.setFocusable(false);
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                queryField.setText("");
                resultPanel.setViewportView(null);
                resultPanel.revalidate();
                resultPanel.repaint();
            }
            
        }
    );

    /*Result Setting */
        resultPanel.setBounds(100, 300, 1100, 320);
        resultPanel.setFont(new Font(null, Font.ROMAN_BASELINE, 20));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setForeground(Color.BLACK);
        resultPanel.setVisible(true);

        this.add(queryLabel);
        this.add(queryField);
        this.add(runButton);
        this.add(clearButton);
        this.add(resultLabel);
        this.add(resultPanel);
    }
}