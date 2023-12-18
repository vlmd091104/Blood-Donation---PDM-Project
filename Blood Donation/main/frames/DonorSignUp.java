package main.frames;

import java.awt.Color;
import java.awt.event.ActionEvent;

import main.encrypt.Encrypt;
import main.sql.ConnectSQL;
import javax.swing.*;

public class DonorSignUp extends SignUpStruct{
    
    JLabel lbGender = new JLabel("Gender:");
    JLabel lbDoB = new JLabel("Date of birth (yyyy/MM/dd):");
    JTextField tfDoB = new JTextField();    
    JComboBox<String> box = new JComboBox<>();

    public DonorSignUp(){
        super();

        lbGender.setBounds(100, 350, 300, 50);
        lbGender.setFont(mainFont);
        lbGender.setForeground(Color.BLACK);

        lbDoB.setBounds(100, 410, 500, 50);
        lbDoB.setFont(mainFont);
        lbDoB.setForeground(Color.BLACK);

        box.setBounds(400, 350, 150, 50);
        box.setBackground(Color.WHITE);
        box.setForeground(Color.BLACK);
        box.setFont(mainFont);
        box.addItem("Male");
        box.addItem("Female");
        box.setEditable(false);

        tfDoB.setBounds(600, 410, 300, 50);
        tfDoB.setBackground(Color.WHITE);
        tfDoB.setForeground(Color.BLACK);
        tfDoB.setFont(mainFont);

        this.add(lbGender);
        this.add(lbDoB);
        this.add(box);
        this.add(tfDoB);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btSignUp){
            if(tfDoB.getText().trim().isEmpty() || super.isFieldNull()){
                JOptionPane.showMessageDialog(null, "There is some empty fields, pls fill in the empty", "Message", JOptionPane.WARNING_MESSAGE);
            }else{
                // ConnectSQL.addPassword("donor" ,HomeStruct.getID(),HomeStruct.user, Encrypt.encrypt(HomeStruct.getStrToEncrypt()));
                boolean isComplete = super.signUpCompleted(ConnectSQL.addData("donor", HomeStruct.getID(), this.tfFirstName.getText(), this.tfLastName.getText(), this.tfDoB.getText(), (String)this.box.getSelectedItem(), this.tfEmail.getText(), this.tfPhone.getText(), this.tfAddress.getText(), null));
                if(isComplete == true){
                    ConnectSQL.addPassword("donor" ,HomeStruct.getID(),HomeStruct.user, Encrypt.encrypt(HomeStruct.getStrToEncrypt()));
                    this.dispose();
                    DashBoardStruct run = new DonorDashBoard();
                    run.setVisible(true);
                }
            }
        }
        if(e.getSource() == btClear){
            boolean isClear = super.confirmToClear();
            if(isClear == true){
                super.clearContent();
                this.tfDoB.setText("");
            }
        }
        if(e.getSource() == btBack){
            int confirmToClear = JOptionPane.showConfirmDialog(null, "Are you sure to exit", "Confirm ?", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(confirmToClear == JOptionPane.YES_OPTION){
                super.processInBackGround("donor");
            }
        }
    }
}
