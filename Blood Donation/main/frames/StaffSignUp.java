package main.frames;

import java.awt.Color;
import java.awt.event.ActionEvent;

import main.encrypt.Encrypt;
import main.sql.ConnectSQL;
import javax.swing.*;

public class StaffSignUp extends SignUpStruct{

    JLabel lbRole = new JLabel("Role:");
    JTextField tfRole = new JTextField();

    public StaffSignUp(){
        super();

        lbRole.setBounds(100, 350, 300, 50);
        lbRole.setFont(mainFont);
        lbRole.setForeground(Color.BLACK);

        tfRole.setBounds(400, 350, 600, 50);
        tfRole.setFont(mainFont);
        tfRole.setForeground(Color.BLACK);

        this.add(lbRole);
        this.add(tfRole);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btSignUp){
            if(tfRole.getText().trim().isEmpty() || super.isFieldNull()){
                JOptionPane.showMessageDialog(null, "There is some empty fields, pls fill in the empty", "Message", JOptionPane.WARNING_MESSAGE);
            }else{
                // ConnectSQL.addPassword("staff", HomeStruct.getID(),HomeStruct.user, Encrypt.encrypt(HomeStruct.getStrToEncrypt()));
                boolean isComplete = super.signUpCompleted(ConnectSQL.addData("staff", HomeStruct.getID(),this.tfFirstName.getText(), this.tfLastName.getText(), null, null, this.tfEmail.getText(), this.tfPhone.getText(), this.tfAddress.getText(), this.tfRole.getText()));
                if(isComplete == true){
                    ConnectSQL.addPassword("staff", HomeStruct.getID(),HomeStruct.user, Encrypt.encrypt(HomeStruct.getStrToEncrypt()));
                    this.dispose();
                    DashBoardStruct run = new StaffDashBoard();
                    run.setVisible(true);
                }
            }
        }
        if(e.getSource() == btClear){
            boolean isClear = super.confirmToClear();
            if(isClear == true){
                super.clearContent();
                this.tfRole.setText("");
            }
        }
        if(e.getSource() == btBack){
            int confirmToClear = JOptionPane.showConfirmDialog(null, "Are you sure to exit", "Confirm ?", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(confirmToClear == JOptionPane.YES_OPTION){
                super.processInBackGround("staff");
            }
        }
    }
}